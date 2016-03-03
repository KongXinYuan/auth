<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>detail</title>
</head>

<body>
	<c:choose>
		<c:when test="${!empty user}">
			<div class="detail-page">
				<h2>用户信息</h2>
				<div class="detail-section">
					<h3>基本信息</h3>
					<div class="row detail-row">
						<div class="span8">
							<a href="#" class="thumbnail"> <img
								src="${user.portraitSmallPath}" alt="头像" width="200"
								height="200" />
							</a>
						</div>
					</div>
					<div class="row detail-row">
						<div class="span8">
							<label>用户名：</label><span class="detail-text">${user.username}</span>
						</div>
						<div class="span8">
							<label>id：</label><span class="detail-text">${user.id}</span>
						</div>
						<div class="span8">
							<label>昵称：</label><span class="detail-text">${user.nickname}</span>
						</div>
					</div>
					<div class="row detail-row">
						<div class="span8">
							<label>姓名：</label><span class="detail-text">${user.realname}</span>
						</div>
						<div class="span8">
							<label>邮箱：</label><span class="detail-text">${user.email}</span>
						</div>
						<div class="span8">
							<label>电话：</label><span class="detail-text">${user.tel}</span>
						</div>
					</div>
					<div class="row detail-row">
						<div class="span8">
							<label>性别：</label><span class="detail-text">${user.sex}</span>
						</div>
						<div class="span8">
							<label>生日：</label><span class="detail-text">${user.birthday}</span>
						</div>
						<div class="span8">
							<label>签名：</label><span class="detail-text">${user.description}</span>
						</div>
					</div>
				</div>

				<div class="detail-section">
					<h3>登录记录</h3>
					<div class="row detail-row">
						<div class="span24">
							<div id="grid"></div>
						</div>
					</div>
				</div>
			</div>


			<script type="text/javascript">
				BUI.use([ 'bui/grid', 'bui/data' ], function(Grid, Data) {
					var Grid = Grid, Store = Data.Store, columns = [ {
						title : '用户id',
						dataIndex : 'userId',
						width : 100
					}, {
						title : '登录ip',
						dataIndex : 'ipAdr',
						width : 100
					}, {
						title : '登录时间',
						dataIndex : 'gmtLogin',
						width : 200
					} ];

					/**
					 * 自动发送的数据格式：
					 *  1. start: 开始记录的起始数，如第 20 条,从0开始
					 *  2. limit : 单页多少条记录
					 *  3. pageIndex : 第几页，同start参数重复，可以选择其中一个使用
					 *
					 * 返回的数据格式：
					 *  {
					 *     "rows" : [{},{}], //数据集合
					 *     "results" : 100, //记录总数
					 *     "hasError" : false, //是否存在错误
					 *     "error" : "" // 仅在 hasError : true 时使用
					 *   }
					 * 
					 */
					var store = new Store({
						url : '${ctx}/user/loginlog.json',
						autoLoad : true, //自动加载数据
						params : { //配置初始请求的参数
							userId : '${user.id}'
						},
						pageSize : 20
					// 配置分页数目
					}), grid = new Grid.Grid({
						render : '#grid',
						columns : columns,
						loadMask : true, //加载数据时显示屏蔽层
						store : store,
						// 底部工具栏
						bbar : {
							// pagingBar:表明包含分页栏
							pagingBar : true
						}
					});

					grid.render();
				});
			</script>
		</c:when>
		<c:otherwise>
			<p style="color: red">不存在此用户</p>
		</c:otherwise>
	</c:choose>

</body>
</html>