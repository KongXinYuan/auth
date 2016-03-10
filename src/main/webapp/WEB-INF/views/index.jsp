<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="虚谷若谷,06libenli@163.com" />
<meta name="robots" content="none">
<meta name="description" content="授权认证系统 powered by kent">
<link rel="shortcut icon" href="/favicon.ico" />

<link href="${ctx}/static/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/main.css" rel="stylesheet" type="text/css" />
</head>

<div class="header">
	<div class="dl-title">
		<a href="${ctx}" target="_blank"> <span class="lp-title-port">用户</span><span class="dl-title-text">管理系统</span>
		</a>
	</div>

	<div class="dl-log">欢迎您，<span class="dl-log-user">${admin}</span>
		<a href="#" onclick="$('#logout_form').submit();" title="退出系统" class="dl-log-quit">[退出]</a>
		<form:form id="logout_form" action="${ctx}/logout" method="post" />
	</div>
</div>
<div class="content">
	<div class="dl-main-nav">
		<ul id="J_Nav" class="nav-list ks-clear">
			<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">首页</div></li>
			<li class="nav-item"><div class="nav-item-inner nav-permission">软件管理</div></li>
			<li class="nav-item"><div class="nav-item-inner nav-user">用户管理</div></li>
			<li class="nav-item"><div class="nav-item-inner nav-monitor">卡密管理</div></li>
		</ul>
	</div>
	<ul id="J_NavContent" class="dl-tab-conten">

	</ul>
</div>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bui.js" type="text/javascript"></script>
<script src="${ctx}/static/js/config.js" type="text/javascript"></script>
<script>
	BUI.use('common/main', function() {
		var config = [ {
			id : 'admin',
			homePage : 'welcome',
			menu : [ {
				text : '管理员',
				items : [ {
					id : 'welcome',
					text : '欢迎页面',
					href : '${ctx}/admin/home',
					closeable : false
				}, {
					id : 'loglogin',
					text : '登录日志',
					href : '${ctx}/admin/loglogin'
				} ]
			}, {
				text : '后台账号',
				items : [ {
					id : 'list',
					text : '账号列表',
					href : '${ctx}/admin/list'
				} ]
			} ]
		}, {
			id : 'soft',
			menu : [ {
				text : '软件管理',
				items : [ {
					id : 'list',
					text : '软件列表',
					href : '${ctx}/soft/list'
				} ]
			} ]
		}, {
			id : 'user',
			menu : [ {
				text : '用户管理',
				items : [ {
					id : 'list',
					text : '用户列表',
					href : '${ctx}/user/list'
				} ]
			}, {
				text : '批量操作',
				items : [ {
					id : 'batch',
					text : '批量操作',
					href : '${ctx}/user/batch'
				} ]
			}, {
				text : '用户日志',
				items : [ {
					id : 'logrecharge',
					text : '用户充值日志',
					href : '${ctx}/user/logrecharge'
				}, {
					id : 'loglogin',
					text : '用户登录列表',
					href : '${ctx}/user/loglogin'
				}, {
					id : 'logpublic',
					text : '公用账号日志',
					href : '${ctx}/user/logpublic'
				} ]
			} ]
		}, {
			id : 'cdkey',
			menu : [ {
				text : '卡密管理',
				items : [ {
					id : 'list',
					text : '卡密列表',
					href : '${ctx}/cdkey/list'
				}, {
					id : 'keyset',
					text : '卡类设置',
					href : '${ctx}/cdkey/keyset'
				}, {
					id : 'batch',
					text : '批量操作',
					href : '${ctx}/cdkey/batch'
				} ]
			} ]
		} ];

		new PageUtil.MainPage({
			modulesConfig : config
		});
	});
</script>
</body>
</html>



