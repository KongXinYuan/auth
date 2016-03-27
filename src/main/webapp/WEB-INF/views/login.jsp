<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="author" content="虚谷若谷,06libenli@163.com" />
    <meta name="robots" content="none">
    <meta name="description" content="授权认证系统 powered by kent">
    <link rel="icon" href="./favicon.ico">
    <title>登录</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
	    #loginform{
	    	max-width: 280px;
    		padding: 15px;
	    	margin: 0 auto;
	    }
	    
	    body {
		    padding-top: 40px;
		    padding-bottom: 40px;
		    background-color: #eee;
		}
    </style>
</head>
<body>

<div class="container">

    <form:form id="loginform" class="form-horizontal" action="/login" method="post">
		<div class="form-group">
			<h2 class="form-signin-heading">用户管理系统</h2>
		</div>
	    <div class="form-group">
	        <label for="inputUsername" class="control-label sr-only">用户名</label>
	        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="用户名" autofocus>
	    </div>
	    <div class="form-group">
	    	<label for="inputPassword" class="control-label sr-only">密码</label>
	    	<input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码">
	    </div>
		<div class="form-group">
	        <div class="checkbox">
	            <label>
	                <input type="checkbox" name="remember-me">记住我
	            </label>
	        </div>
		</div>
		<c:if test="${error}">
		<div class="form-group">
			<div class="alert alert-danger alert-dismissible" role="alert">
			   <a href="#" class="close" data-dismiss="alert">
			      &times;
			   </a>
			   用户名或密码错误
			</div>
		</div>
		</c:if>
		<div class="form-group">
			<button class="btn btn-primary" type="submit">登录</button>
		</div>
    </form:form>
</div>
<!-- js文件 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/formValidation.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.0/js/language/zh_CN.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/framework/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#loginform').formValidation({
        framework: 'bootstrap',
        icon: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    }
                }
            }
        }
    });
});
</script>

</body>
</html>
