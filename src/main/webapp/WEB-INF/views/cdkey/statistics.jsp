<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>注册卡统计表</title>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-striped table-hover" id="admintable">
			<thead>
				<tr>
					<th>所属</th>
					<th>上级账号</th>
					<th>卡类</th>
					<th>总量</th>
					<th>锁定</th>
					<th>使用</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statistics}" var="item">
					<tr>
						<td>${item.adminname}</td>
						<td>${item.parentname}</td>
						<td>${item.keyname}</td>
						<td>${item.total}</td>
						<td>${item.locked}</td>
						<td>${item.used}</td>
						<td>${item.deleted}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>