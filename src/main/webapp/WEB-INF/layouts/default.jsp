<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>灵犀后台:<sitemesh:title /></title>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link href="${ctx}/static/css/dpl-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/static/css/bui-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/static/css/page.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/static/css/default.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/static/js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bui.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/config.js" type="text/javascript"></script>
	<script type="text/javascript">
		BUI.use('common/page');
	</script>
	<sitemesh:head />
</head>

<body>
	<div class="container">
		<sitemesh:body />
	</div>
</body>
</html>