<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>欢迎</title>
</head>
<body>
	<div class="doc-content">
		<h3>个人资料</h3>
		<hr>
		<form action="" class="form-horizontal form-horizontal-simple">
			<div class="control-group">
				<label class="control-label">用户名：</label>
				<div class="controls">
					<span class="control-text">${admin.username}</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">级别：</label>
				<div class="controls">
					<span class="control-text"><c:choose>
							<c:when test="${0==admin.level}">作者</c:when>
							<c:when test="${1==admin.level}">总代理</c:when>
							<c:when test="${2==admin.level}">代理</c:when>
						</c:choose></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">上次登录时间：</label>
				<div class="controls">
					<span class="control-text"> <fmt:formatDate value="${admin.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">上次登录ip：</label>
				<div class="controls">
					<span class="control-text">${admin.lastloginip}</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">当前余额：</label>
				<div class="controls">
					<span class="control-text">${admin.money}元</span>
				</div>
			</div>
		</form>

		<div class="detail-section">
			<h3>登录记录</h3>
			<hr>
			<div class="detail-row">
				<table class="table table-head-bordered">
					<thead>
						<tr>
							<th>id</th>
							<th>ip地址</th>
							<th>登录时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${loglogin}" var="log">
							<tr>
								<td>${log.id}</td>
								<td>${log.loginip}</td>
								<td><fmt:formatDate value="${log.logintime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>