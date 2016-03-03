<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css"/>
</head>

<body class="login">

	<form:form class="login_m" action="${ctx}/login" method="post">
		<div class="login_logo">
			<img src="${ctx}/static/images/logo.png" width="196" height="46">
		</div>
		<div class="login_boder">
			<div class="login_padding">
				<h2>用户名</h2>
				<label> <input type="text" id="username" name="username"
					class="txt_input" placeholder="username">
				</label>
				<h2>密码</h2>
				<label> <input type="password" id="password" name="password"
					class="txt_input" placeholder="password">
				</label>
				<div class="rem_sub">
					<div class="rem_sub_l">
						<input type="checkbox" name="rememberMe" id="rememberMe"> <label
							for="rememberMe">记住</label>
					</div>
					<label> <input type="submit" class="sub_button"
						name="button" id="button" value="登录" style="opacity: 0.7;">
					</label>
				</div>
			</div>
		</div>
		<div>
		<c:if test="${error}">
			<p style="color:red">用户名或密码错误</p>
		</c:if>
		</div>
	</form:form>
</body>
</html>
