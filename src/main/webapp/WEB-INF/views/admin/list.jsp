<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<style type="text/css">
    #addform {
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
		<table class="table table-striped table-hover" id="admintable">
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>id</th>
					<th>级别</th>
					<th>用户名</th>
					<th>上级账号</th>
					<th>创建时间</th>
					<th>上次登录时间</th>
					<th>上次登录ip</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${results}" var="admin">
					<tr>
						<td><input type="checkbox" /></td>
						<td>${admin.id}</td>
						<td>${admin.level}</td>
						<td>${admin.username}</td>
						<td>${admin.parentid}</td>
						<td><fmt:formatDate value="${admin.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${admin.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${admin.lastloginip}</td>
						<td>
							 <c:choose>
							 <c:when test="${admin.lock}">
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
							<label for="inputusername" class="form-label sr-only">用户名</label>
							<input type="text" id="inputusername" name="username" class="form-control" placeholder="用户名">
						</div>
						<div class="form-group">
							<label for="inputpassword" class="form-label sr-only">密码</label>
							<input type="password" id="inputpassword" name="password" class="form-control" placeholder="密码">
						</div>
						<div class="form-group">
							<label for="inputpasswordconfirm" class="form-label sr-only">确认密码</label>
							<input type="password" id="inputpasswordconfirm" name="confirmpassword" class="form-control" placeholder="确认密码">
						</div>
			        	<div class="form-group">
							<label for="inputlevel" class="form-label sr-only">级别</label>
							<div>
							    <select class="form-control" id="inputlevel" name="level">
							        <option value="1">总代理</option>
							        <option value="2">代理</option>
							    </select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputmoney" class="form-label sr-only">余额</label>
							<input type="text" id="inputmoney" name="money" class="form-control" placeholder="当前余额" value=0>
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
							<label for="inputusername" class="form-label sr-only">用户名</label>
							<input type="text" id="inputusername" name="username" class="form-control" placeholder="用户名">
						</div>
						<div class="form-group">
							<label for="inputpassword" class="form-label sr-only">密码</label>
							<input type="password" id="inputpassword" name="password" class="form-control" placeholder="密码">
						</div>
			        	<div class="form-group">
							<label for="inputlevel" class="form-label sr-only">级别</label>
							<div>
							    <select class="form-control" id="inputlevel" name="level">
							        <option value="1">总代理</option>
							        <option value="2">代理</option>
							    </select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputmoney" class="form-label sr-only">余额</label>
							<input type="text" id="inputmoney" name="money" class="form-control" placeholder="当前余额" value=0>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" tabindex="0" role="button" data-trigger="focus">取消</button>
					<button type="button" class="btn btn-primary" data-action="add" tabindex="0" role="button" data-trigger="focus">确定</button>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/static/js/checkbox.js"></script>
	<script src="${ctx}/static/js/adminlist.js"></script>
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