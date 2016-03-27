<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>管理员列表</title>
<style type="text/css">
    #addform {
        max-width: 300px;
		margin: auto;
    }
    
    #addModal #delModal{
        max-width: 420px;
    }
    
    .pagination{
    	float: right;
    }
    .form-inline{
    	margin: 5px;
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
				<c:forEach items="${admins}" var="admin">
					<tr>
						<td><input type="checkbox" /></td>
						<td>${admin.id}</td>
						<td>${admin.role}</td>
						<td>${admin.username}</td>
						<td>${admin.parentname}</td>
						<td><fmt:formatDate value="${admin.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${admin.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${admin.lastloginip}</td>
						<td>
							 <a data-action="edit" data-toggle="modal" data-target="#editModal" href="#" title="编辑"><span class="glyphicon glyphicon-edit" tabindex="0" role="button" data-trigger="focus"></span></a>
							 <a data-action="del" data-toggle="modal" data-target="#delselModal" href="#" title="删除"><span class="glyphicon glyphicon-remove" tabindex="0" role="button" data-trigger="focus"></span></a>
							 <c:choose>
							 <c:when test="${admin.status eq '已锁定'}">
							 	<a data-action="lock" data-toggle="modal" href="#" title="解锁"><span class="glyphicon glyphicon-ok-circle" tabindex="0" role="button" data-trigger="focus"></span></a>
							 </c:when>
							 <c:when test="${admin.status eq '激活'}">
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
			        	<div class="form-group">
							<label for="inputusername" class="form-label sr-only">用户名</label>
							<input type="text" id="inputusername" name="username" class="form-control" placeholder="用户名">
						</div>
						<div class="form-group">
							<label for="inputpassword" class="form-label sr-only">密码</label>
							<input type="password" id="inputpassword" name="password" class="form-control" placeholder="密码">
						</div>
			        	<div class="form-group">
							<label for="inputRole" class="form-label sr-only">级别</label>
							<div>
							    <select class="form-control" id="inputRole" name="role">
							    	<sec:authorize access="hasRole('ROLE_OWNER')">
							        <option value="1">总代理</option>
							        </sec:authorize>
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
					<div class="form-inline">
						<div class="form-group">
						<label class="form-label sr-only">用户名</label>
						<p id="updateusername" class="form-control-static"></p>
						</div>
						<div class="form-group">
						<label class="form-label sr-only">级别</label>
						<p id="updaterole" class="form-control-static"></p>
						</div>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="form-label">密码</label>
							<input type="password" id="updatepassword" class="form-control" placeholder="密码">
						</div>
						<button type="button" class="btn btn-default" data-action="updatepassword" tabindex="0" role="button" data-trigger="focus">确定</button>
					</div>
					<div class="form-inline">
						<div class="form-group">
							<label class="form-label">余额</label>
							<input type="text" id="updatemoney" class="form-control" placeholder="当前余额">
						</div>
						<button type="button" class="btn btn-default" data-action="updatemoney" tabindex="0" role="button" data-trigger="focus">确定</button>
					</div>
					<table class="table table-striped table-hover" id="adminkeysettable">
						<thead>
							<tr>
								<th>软件名</th>
								<th>卡类</th>
								<th>零售价</th>
								<th>代理价</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<select id="editsoft" class="form-control" name="softid" >
										<option value="">选择软件</option>
										<c:forEach items="${softs}" var="soft">
										<option value="${soft.id}">${soft.softname}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select id="editkeyset" class="form-control" name="keysetid" >
									</select>
								</td>
								<td id="editretailprice"></td>
								<td><input type="text" id="editsellprice" name="sellprice" placeholder="代理价格"></td>
								<td>
									<button type="button" class="btn btn-default" data-action="enpower" tabindex="0" role="button" data-trigger="focus">授权</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" data-trigger="focus">确定</button>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/static/js/checkbox.js"></script>
	<script src="${ctx}/static/js/adminlist.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#addModal #inputrole').multiselect({nonSelectedText:"请选择"});

		
		
		var keysetsoptions=[];
		$("#editsoft").multiselect({nonSelectedText:"请选择",
			onChange: function(option, checked){
				if(checked){
					var adminid = edititem.children("td:eq(1)").html();
					var softid = option.val();
					if(softid==''){
						$('#editkeyset').multiselect('dataprovider',[]).multiselect('rebuild');
						$("#editretailprice").html(0);
						return;
					}
					$.post("/admin/power/list.json",{adminid:adminid,softid:softid},function(result){
	                	keysetsoptions = result["powers"];
	                	options=[];
	                	if(null!=keysetsoptions){
		                	$.each(keysetsoptions,function(index,power) {
		                		options.push({label: power.keyname+'--['+power.cday+'天]', value: power.id});
		                	});
	                	}
	                	$('#editkeyset').multiselect('dataprovider',options).multiselect('rebuild');
	                	if(null!=keysetsoptions){
	                		$("#editretailprice").html(keysetsoptions[0]["retailprice"]);
	                	}
					},"json");
				}
			}
		});

		$("#editkeyset").multiselect({nonSelectedText:"请选择",
			onChange: function(option, checked){
				if(checked){
					$.each(keysetsoptions,function(index,power){
						if(power.id==option.val()){
							$("#editretailprice").html(power.retailprice);return;
						}
					});
				}
			}
		});
		
		
		//获取用户数据
		var edititem;
		$('#editModal').on('show.bs.modal', function(event) {
			edititem = $(event.relatedTarget).closest("tr");
			var button = $(this).popover('destroy');
			var adminid = edititem.children("td:eq(1)").html();
			$.post("/admin/detail.json",{adminid:adminid},function(result){
                if(true == result["hasError"]){
                    //提示错误
                	$('#editModal').modal('hide');
                    button.popover({content:"错误:"+result["error"]}).popover('show');
                }else if(true == result["success"]){
                	var admin = result["admin"];
                	$('#updateusername').html(admin["username"]);
                	$('#updaterole').html(admin["role"]);
                	$("#updatemoney").val(admin["money"]);
                	var powers=result["powers"];
                	if(null==powers)return;
                	$.each(powers,function(index,power) {
                		addadminkeysettable(power["keysetid"],power['softname'],power['keyname'],power['retailprice'],power['sellprice']);
                	});
                }
			},"json");
		}).on('hide.bs.modal', function(event) {
			$("#adminkeysettable tbody tr:gt(0)").remove();
		});
		
		function addadminkeysettable(keysetid,softname,keyname,retailprice,sellprice){
    		$("#adminkeysettable tbody ").append("<tr data-keysetid='"+keysetid+"'>"
    				+"<td>"+softname+"</td>"
    				+"<td>"+keyname+"</td>"
    				+"<td>"+retailprice+"</td>"
    				+"<td><input type='text' value='"+sellprice+"'></td><td>"
    				+'<button type="button" class="btn btn-default btn-xs" data-action="updateadminkeyset" tabindex="0" role="button" data-trigger="focus">确定</button>'
    				+'<button type="button" class="btn btn-default btn-xs" data-action="deleteadminkeyset" tabindex="0" role="button" data-trigger="focus">删除</button>'
    				+"</td></tr>");
		};

		$("#adminkeysettable tbody").on('click',"[data-action='updateadminkeyset']",function(){
			 var button = $(this);
			 var adminid = edititem.children("td:eq(1)").html();
			 var keysetid = button.closest("tr").data("keysetid");
			 var sellprice = button.closest("tr").children("td").eq(3).children("input").val();
			 $.post("/admin/power/update.json",{adminid:adminid,keysetid:keysetid,sellprice:sellprice},function(result){
	                if(true == result["hasError"]){
	                    button.popover({content:"错误:"+result["error"]}).popover('show');
	                }else if(true == result["success"]){
	                    button.popover({content:"修改成功"}).popover('show');
	                }
			 },"json");
		});

		$("#adminkeysettable tbody").on('click',"[data-action='deleteadminkeyset']",function(){
			 var button = $(this).popover('destroy');
			 var adminid = edititem.children("td:eq(1)").html();
			 var keysetid = button.closest("tr").data("keysetid");
			 $.post("/admin/power/del.json",{adminid:adminid,keysetid:keysetid},function(result){
	                if(true == result["hasError"]){
	                    button.popover({content:"错误:"+result["error"]}).popover('show');
	                }else if(true == result["success"]){
	                	button.closest("tr").remove();
	                }
			 },"json");
		 });
		
		
		 $("[data-action='updatepassword']").on('click',function () {
			 var button = $(this).popover('destroy');
			 var adminid = edititem.children("td:eq(1)").html();
			 var pwd=$("#updatepassword").val();
			 $.post("/admin/updatepassword.json",{adminid:adminid,password:pwd},function(result){
	                if(true == result["hasError"]){
	                    button.popover({content:"错误:"+result["error"]}).popover('show');
	                }else if(true == result["success"]){
	                    button.popover({content:"修改成功"}).popover('show');
	                }
			 },"json");
		 });
		
		 $("[data-action='updatemoney']").on('click',function () {
			 var button = $(this).popover('destroy');
			 var adminid = edititem.children("td:eq(1)").html();
			 var money=$("#updatemoney").val();
			 $.post("/admin/updatemoney.json",{adminid:adminid,money:money},function(result){
	                if(true == result["hasError"]){
	                    button.popover({content:"错误:"+result["error"]}).popover('show');
	                }else if(true == result["success"]){
	                    button.popover({content:"修改成功"}).popover('show');
	                }
			 },"json");
		 });

		 //授权
		 $("[data-action='enpower']").on('click',function () {
			 var button = $(this).popover('destroy');
			 var adminid = edititem.find("td:eq(1)").html();
			 var softid = button.closest("tr").find("#editsoft").val();
			 var keysetid = button.closest("tr").find("#editkeyset").val();
			 var sellprice = button.closest("tr").find("#editsellprice").val();
			 $.post("/admin/power/add.json",{adminid:adminid,softid:softid,keysetid:keysetid,sellprice:sellprice},function(result){
	                if(true == result["hasError"]){
	                    button.popover({content:"错误:"+result["error"]}).popover('show');
	                }else if(true == result["success"]){
	                    button.popover({content:"修改成功"}).popover('show');
	                    var power=result["power"];
	                    addadminkeysettable(power["keysetid"],power['softname'],power['keyname'],power['retailprice'],power['sellprice']);
	                }
			 },"json");
		 });
		 
		 
		 

	});
	
	
	</script>
</body>

</html>