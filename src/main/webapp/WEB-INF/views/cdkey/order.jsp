<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>订单日志</title>
<style type="text/css">
    .pagination{
    	float: right;
    }
</style>
</head>
<body>
	<div class="table-responsive">
		<table class="table table-striped table-hover" id="admintable">
			<thead>
				<tr>
					<th>id</th>
					<th>订单号</th>
					<th>管理员</th>
					<th>上级账号</th>
					<th>软件</th>
					<th>卡类</th>
					<th>数量</th>
					<th>开始id</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="log">
					<tr>
						<td>${log.id}</td>
						<td>${log.ordernum}</td>
						<td>${log.adminname}</td>
						<td>${log.parentname}</td>
						<td>${log.softname}</td>
						<td>${log.keyname}</td>
						<td>${log.keycount}</td>
						<td>${log.beginid}</td>
						<td><fmt:formatDate value="${log.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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