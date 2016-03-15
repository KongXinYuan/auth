<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>软件列表</title>
<style type="text/css">
    #addform, #editform{
        max-width: 300px;
		margin: auto;
    }

    .modal-dialog{
        max-width: 420px;
    }
    .pagination{
    	float: right;
    }
</style>
</head>
<body>
	<div class="form-inline">
		<button class="btn btn-default" type="button" data-toggle="modal" data-target="#addModal"><span class="glyphicon glyphicon-plus"></span>添加</button>
		<button class="btn btn-default" type="button" data-toggle="modal" data-target="#delselModal"><span class="glyphicon glyphicon-minus"></span>删除</button>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-hover" id="softtable">
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>id</th>
					<th>softcode</th>
					<th>softkey</th>
					<th>softname</th>
					<th>intervaltime</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${results}" var="soft">
					<tr>
						<td><input type="checkbox" /></td>
						<td>${soft.id}</td>
						<td>${soft.softcode}</td>
						<td>${soft.softkey}</td>
						<td>${soft.softname}</td>
						<td>${soft.intervaltime}</td>
						<td>
							 <c:choose>
							 <c:when test="${soft.lock}">
							 	<a data-action="lock" data-toggle="modal" href="#" title="解锁"><span class="glyphicon glyphicon-ok-circle" tabindex="0" role="button" data-trigger="focus"></span></a>
							 </c:when>
							 <c:otherwise>
							 	<a data-action="lock" data-toggle="modal" href="#" title="锁定"><span class="glyphicon glyphicon-ban-circle" tabindex="0" role="button" data-trigger="focus"></span></a>
							 </c:otherwise>
							 </c:choose>
							 <a data-action="del" data-toggle="modal" data-target="#delselModal" href="#" title="删除"><span class="glyphicon glyphicon-remove" tabindex="0" role="button" data-trigger="focus"></span></a>
							 <a data-action="edit" data-toggle="modal" data-target="#editModal" href="#" title="编辑"><span class="glyphicon glyphicon-edit" tabindex="0" role="button" data-trigger="focus"></span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul id="pagination" class="pagination"></ul>
	</div>

	<div class="modal fade" id="delselModal" tabindex="-1" role="dialog" aria-labelledby="delselModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="delselModalLabel">删除</h4>
				</div>
				<div class="modal-body">
					<p id="delcontent">确认删除这一行么</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-action="delsel">删除</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addModalLabel">添加</h4>
				</div>
				<div class="modal-body">
					<form:form class="form-horizontal" id="addform">
			        	<div class="form-group">
							<label for="inputsoftname" class="form-label sr-only">软件名</label>
							<input type="text" id="inputsoftname" name="softname" class="form-control" placeholder="软件名">
						</div>
						<div class="form-group">
							<label for="inputintervaltime" class="form-label sr-only">intervaltime</label>
							<input type="text" id="inputintervaltime" name="intervaltime" class="form-control" placeholder="intervaltime" value=120>
						</div>
						<div class="form-group">
							<label for="inputclientpubkey" class="form-label sr-only">clientpubkey</label>
							<input type="text" id="inputclientpubkey" name="clientpubkey" class="form-control" placeholder="clientpubkey">
						</div>
						<div class="form-group">
							<label for="inputserverprivkey" class="form-label sr-only">serverprivkey</label>
							<input type="text" id="inputserverprivkey" name="serverprivkey" class="form-control" placeholder="serverprivkey">
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" tabindex="0" role="button" data-trigger="focus">取消</button>
					<button type="button" class="btn btn-primary" data-action="add" tabindex="0" role="button" data-trigger="focus">添加</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editModalLabel">编辑</h4>
				</div>
				<div class="modal-body">
					<form:form class="form-horizontal" id="editform">
						
			        	<div class="form-group">
							<input id="inputsoftid" name="softid" hidden=true>
						</div>
			        	<div class="form-group">
							<label for="inputsoftname" class="form-label col-xs-4">软件名</label>
							<input type="text" id="inputsoftname" class="form-control-static col-xs-8" disabled>
						</div>
						<div class="form-group">
							<label for="inputsoftcode" class="form-label col-xs-4">softcode</label>
							<input type="text" id="inputsoftcode" class="form-control-static col-xs-8" disabled>
						</div>
						<div class="form-group">
							<label for="inputsoftkey" class="form-label col-xs-4">softkey</label>
							<input type="text" id="inputsoftkey" class="form-control-static col-xs-8" disabled>
						</div>
						<div class="form-group">
							<label for="inputintervaltime" class="form-label col-xs-4">intervaltime</label>
							<input type="text" id="inputintervaltime" name="intervaltime" class="form-control col-xs-8" placeholder="intervaltime">
						</div>
						<div class="form-group">
							<label for="inputclientpubkey" class="form-label col-xs-4">clientpubkey</label>
							<input type="text" id="inputclientpubkey" name="clientpubkey" class="form-control col-xs-8" placeholder="clientpubkey">
						</div>
						<div class="form-group">
							<label for="inputserverprivkey" class="form-label col-xs-4">serverprivkey</label>
							<input type="text" id="inputserverprivkey" name="serverprivkey" class="form-control col-xs-8" placeholder="serverprivkey">
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" tabindex="0" role="button" data-trigger="focus">取消</button>
					<button type="button" class="btn btn-primary" data-action="update" tabindex="0" role="button" data-trigger="focus">确认</button>
				</div>
			</div>
		</div>
	</div>



	<script src="/static/js/checkbox.js"></script>
	<script src="/static/js/softlist.js"></script>
	<script type="text/javascript">
	$(function() {
		/* 初始化页数 */
		var results = '${count}';
		$('#pagination').twbsPagination({
			totalPages : Math.floor(results / 20 + 1),
			visiblePages : 5,
			href : '?pageNo={{pageNo}}&pageSize=20',
			hrefVariable : '{{pageNo}}',
			onPageClick : function(event, page) {
			}
		});
	});
	</script>
</body>

</html>