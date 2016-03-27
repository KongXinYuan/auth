<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>

</head>
<body>
	<div class="doc-content">
		<h3>个人资料</h3>
		<div class="form-horizontal">

			<div class="form-group">
				<label class="col-sm-2 control-label">用户名：</label>
				<p class="col-sm-10 form-control-static">${admin.username}</p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">级别：</label>
				<p class="col-sm-10 form-control-static">${admin.role}</p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">上次登录时间：</label>
				<p class="col-sm-10 form-control-static"> <fmt:formatDate
						value="${admin.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">上次登录ip：</label>
				<p class="col-sm-10 form-control-static">${admin.lastloginip}</p>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">当前余额：</label>
				<p class="col-sm-10 form-control-static">${admin.money}元</p>
			</div>
		</div>

		<h3>登录记录</h3>
		<div class="table-responsive">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>id</th>
						<th>ip地址</th>
						<th>登录时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${loginlogs}" var="log">
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
</body>
</html>



