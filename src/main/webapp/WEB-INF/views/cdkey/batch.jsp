<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>注册卡批量管理</title>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-striped table-hover" id="admintable">
			<thead>
				<tr>
					<th>管理员</th>
					<th>上级账号</th>
					<th>ip</th>
					<th>登录时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logs}" var="log">
					<tr>
						<td>${log.username}</td>
						<td>${log.parentname}</td>
						<td>${log.loginip}</td>
						<td><fmt:formatDate value="${log.logintime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul id="pagination" class="pagination"></ul>
	</div>
	<script type="text/javascript">
	$(function(){
		/* 初始化页数 */
		var results = '${count}';
		$('#pagination').twbsPagination({
			totalPages : Math.floor(results / 20 + 1),
			visiblePages : 5,
			href : '?pageNo={{pageNo}}&pageSize=20&',
			hrefVariable : '{{pageNo}}',
			onPageClick : function(event, page) {
			}
		});
	});
	</script>
</body>

</html>