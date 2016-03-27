<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>财务表</title>
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
					<th>管理员</th>
					<th>账单方向</th>
					<th>相关管理员</th>
					<th>当前金额</th>
					<th>之前金额</th>
					<th>金额变化</th>
					<th>说明</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${finances}" var="log">
					<tr>
						<td>${log.username}</td>
						<td>${log.direction}</td>
						<td>${log.relatedname}</td>
						<td>${log.moneynow}</td>
						<td>${log.moneybefore}</td>
						<td>${log.moneychange}</td>
						<td>${log.disc}</td>
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