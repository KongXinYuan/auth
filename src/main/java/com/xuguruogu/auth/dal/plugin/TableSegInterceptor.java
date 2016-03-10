package com.xuguruogu.auth.dal.plugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *实现的是分表操作
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class TableSegInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(TableSegInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);

		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
		// 可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}

		// 配置对象
		// Configuration configuration = (Configuration)
		// metaStatementHandler.getValue("delegate.configuration");
		// 绑定的sql对象
		// BoundSql boundSql = (BoundSql)
		// metaStatementHandler.getValue("delegate.boundSql");
		// 参数列表会表达为preparestatement之后的set(1,#{username})
		// List<?> parameterMappings = (List<?>)
		// metaStatementHandler.getValue("delegate.boundSql.parameterMappings");
		// 参数映射：#{username}在这里表示为username=kent
		// Map<?, ?> parameterObject = (Map<?, ?>)
		// metaStatementHandler.getValue("delegate.boundSql.parameterObject");

		// mybatis的map文件获得的参数
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// Id后缀为Seg代表需要分表操作
		if (!mappedStatement.getId().matches(".*WithSeg$")) {
			// 传递给下一个拦截器处理
			return invocation.proceed();
		}

		// 获取参数softid
		String softid = (String) metaStatementHandler.getValue("delegate.boundSql.parameterObject.softid");
		if (null == softid || softid.isEmpty()) {
			throw new SQLException("分表操作未找到参数:softid");
		}

		// sql语句
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		if (null == originalSql || !Pattern
				.compile("(.*KSS_SOFT_KEY.*|.*KSS_SOFT_USER.*|.*KSS_SOFT_RECHARGE.*)", Pattern.CASE_INSENSITIVE)
				.matcher(originalSql).matches()) {
			throw new SQLException("分表操作未找到表名:softid");

		}

		// 置换
		String newSql = originalSql.replaceFirst("(?i)KSS_SOFT_KEY", "KSS_SOFT_KEY_" + softid)
				.replaceFirst("(?i)KSS_SOFT_USER", "KSS_SOFT_USER_" + softid)
				.replaceFirst("(?i)KSS_SOFT_RECHARGE", "KSS_SOFT_RECHARGE_" + softid);

		if (logger.isInfoEnabled()) {
			logger.info("\noriginalSql:" + originalSql + "\nnewSql:" + newSql);
		}
		metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		// 传递给下一个拦截器处理
		return invocation.proceed();

	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
		// 次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}