<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><sitemesh:title /></title>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="虚谷若谷,06libenli@163.com" />
	<meta name="robots" content="none">
	<meta name="description" content="授权认证系统 powered by kent">
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link href="${ctx}/static/css/dpl-min.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/static/css/bui-min.css" rel="stylesheet" type="text/css" />
	<sitemesh:head />
</head>

<body>
	<div class="container" style="padding: 15px 20px 0px 15px;">
		<sitemesh:body />
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${ctx}/static/js/bui.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/config.js" type="text/javascript"></script>
	<script type="text/javascript">
		BUI.use('common/page');
	</script>
</body>
</html>