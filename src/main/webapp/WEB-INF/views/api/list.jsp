<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="reqUrl" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<html>
<head>
	<title>Restful API 列表</title>
</head>

<body>

<h1>灵犀文档</h1>

<h2>开发指南</h2>
<ul>
	<li><a href="http://blog.xuguruogu.com/?p=194" target="_blank">rest client调试接口指南</a></li>
</ul>

<h2>API接口</h2>
<ul>
	<li><a href="${reqUrl}/doc/user" target="_blank">用户接口</a></li>
	<li><a href="${reqUrl}/doc/school" target="_blank">学校列表</a></li>
</ul>

</body>
</html>
