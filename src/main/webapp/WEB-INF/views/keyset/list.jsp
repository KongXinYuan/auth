<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>卡类列表-${soft.softname}</title>
<style type="text/css">
    #addform, #editform{
        max-width: 300px;
		margin: auto;
    }

    .modal-dialog{
        max-width: 420px;
    }
</style>
</head>
<body>
	<div class="form-inline">
		<button class="btn btn-default" type="button" data-toggle="modal" data-target="#addModal"><span class="glyphicon glyphicon-plus"></span>添加</button>
		<button class="btn btn-default" type="button" data-toggle="modal" data-target="#delselModal"><span class="glyphicon glyphicon-minus"></span>删除</button>
	</div>
	<div class="table-responsive">
		<table class="table table-striped table-hover" id="keysettable">
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>id</th>
					<th>softid</th>
					<th>名称</th>
					<th>充值时间</th>
					<th>前缀</th>
					<th>售价</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${keysets}" var="keyset">
					<tr>
						<td><input type="checkbox" /></td>
						<td>${keyset.id}</td>
						<td>${keyset.softid}</td>
						<td>${keyset.keyname}</td>
						<td>${keyset.cday}</td>
						<td>${keyset.prefix}</td>
						<td>${keyset.retailprice}</td>
						<td>
							 <a data-action="edit" data-toggle="modal" data-target="#editModal" href="#" title="编辑"><span class="glyphicon glyphicon-edit" tabindex="0" role="button" data-trigger="focus"></span></a>
							 <a data-action="del" data-toggle="modal" data-target="#delselModal" href="#" title="删除"><span class="glyphicon glyphicon-remove" tabindex="0" role="button" data-trigger="focus"></span></a>
							 <c:choose>
							 <c:when test="${keyset.status eq '已锁定'}">
							 	<a data-action="lock" data-toggle="modal" href="#" title="解锁"><span class="glyphicon glyphicon-ok-circle" tabindex="0" role="button" data-trigger="focus"></span></a>
							 </c:when>
							 <c:when test="${keyset.status eq '激活'}">
							 	<a data-action="lock" data-toggle="modal" href="#" title="锁定"><span class="glyphicon glyphicon-ban-circle" tabindex="0" role="button" data-trigger="focus"></span></a>
							 </c:when>
							 </c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
			        	<div class="form-group" hidden="true">
							<input type="text" name="softid" class="form-control" placeholder="softid" value='${soft.id}'>
						</div>
			        	<div class="form-group">
							<label for="inputkeyname" class="form-label sr-only">名称</label>
							<input type="text" id="inputkeyname" name="keyname" class="form-control" placeholder="名称">
						</div>
						<div class="form-group">
							<label for="inputcday" class="form-label sr-only">充值时间</label>
							<input type="text" id="inputcday" name="cday" class="form-control" placeholder="充值时间">
						</div>
						<div class="form-group">
							<label for="inputprefix" class="form-label sr-only">前缀</label>
							<input type="text" id="inputprefix" name="prefix" class="form-control" placeholder="前缀">
						</div>
						<div class="form-group">
							<label for="inputretailprice" class="form-label sr-only">零售价</label>
							<input type="text" id="inputretailprice" name="retailprice" class="form-control" placeholder="零售价">
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
						
			        	<div class="form-group" hidden=true>
							<input id="inputkeysetid" name="keysetid">
						</div>
						<div class="form-group">
							<label for="inputcday" class="form-label col-xs-4">充值时间</label>
							<input type="text" id="inputcday" name="cday" class="form-control col-xs-8" placeholder="cday">
						</div>
						<div class="form-group">
							<label for="inputretailprice" class="form-label col-xs-4">零售价</label>
							<input type="text" id="inputretailprice" name="retailprice" class="form-control col-xs-8" placeholder="retailprice">
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
	<script src="/static/js/keysetlist.js"></script>
</body>

</html>