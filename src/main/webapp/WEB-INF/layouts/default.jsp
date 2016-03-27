<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="虚谷若谷,06libenli@163.com" />
<meta name="robots" content="none">
<meta name="description" content="授权认证系统 powered by kent">
<link rel="icon" href="./favicon.ico">
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/formvalidation/0.6.1/css/formValidation.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css" rel="stylesheet">
<link href="/static/css/default.css" rel="stylesheet">
<sitemesh:head />
<style type="text/css">
	.container{
		width:90%;
	}
</style>
</head>

<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
					aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">用户管理系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasRole('ROLE_OWNER')">
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">软件管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/soft/list">软件列表</a></li>
							<li><a href="#" data-toggle="modal" data-target="#keysetModal">卡类售价设置</a></li>
						</ul>
					</li>
					</sec:authorize>

					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">用户管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal" data-target="#userModal">用户列表</a></li>
							<!-- <li><a href="#">批量操作</a></li> -->
							<li><a href="#">公用账号日志</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">注册卡管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal" data-target="#cdkeyModal">注册卡列表</a></li>
							<li><a href="/cdkey/add">添加注册卡</a></li>
							<!-- <li><a href="/cdkey/batch">批量操作</a></li> -->
							<li><a href="#" data-toggle="modal" data-target="#cdkeyStatisticsModal">注册卡统计表</a></li>
							<li><a href="/cdkey/order">订单管理</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">后台账号<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasAnyRole('OWNER', 'ADMIN')">
							<li><a href="/admin/list">账号列表</a></li>
							</sec:authorize>
							<li><a href="/admin/finance">我的财务日志</a></li>
							<li><a href="/admin/loglogin">登录日志</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a>你好,<sec:authentication property="principal.username"></sec:authentication></a></li>
					<li><a href="#" onclick="$('#logout_form').submit();" title="退出系统">退出系统<span class="sr-only">(current)</span></a></li>
					<form:form id="logout_form" action="/logout" method="post" />
				</ul>
			</div>
		</div>
	</nav>
	

	<div class="modal fade" id="keysetModal" tabindex="-1" role="dialog" aria-labelledby="keysetModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="keysetModalLabel">请选择软件</h4>
				</div>
				<div class="modal-body">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="cdkeyModal" tabindex="-1" role="dialog" aria-labelledby="cdkeyModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cdkeyModalLabel">请选择软件</h4>
				</div>
				<div class="modal-body">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userModalLabel">请选择软件</h4>
				</div>
				<div class="modal-body">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="cdkeyStatisticsModal" tabindex="-1" role="dialog" aria-labelledby="cdkeyStatisticsModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cdkeyStatisticsModalLabel">请选择软件</h4>
				</div>
				<div class="modal-body">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/twbs-pagination/1.3.1/jquery.twbsPagination.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/formValidation.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.0/js/language/zh_CN.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/framework/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.min.js"></script>
<div class="container">
	<sitemesh:body />
</div>
<script type="text/javascript">

$('#keysetModal').on('show.bs.modal', function(event) {
	$("#keysetModal .modal-body").load("/soft/form?path=keyset&second=list")
});

$('#cdkeyModal').on('show.bs.modal', function(event) {
	$("#cdkeyModal .modal-body").load("/soft/form?path=cdkey&second=list")
});

$('#userModal').on('show.bs.modal', function(event) {
	$("#userModal .modal-body").load("/soft/form?path=user&second=list")
});

$('#cdkeyStatisticsModal').on('show.bs.modal', function(event) {
	$("#cdkeyStatisticsModal .modal-body").load("/soft/form?path=cdkey&second=statistics")
});

</script>
</body>
</html>


