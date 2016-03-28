<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<style type="text/css">

.sidebar {
  position: fixed;
  top: 51px;
  bottom: 0;
  left: 0;
  z-index: 1000;
  display: block;
  padding: 20px;
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  background-color: #f5f5f5;
  border-right: 1px solid #eee;
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > a {
  padding-right: 20px;
  padding-left: 20px;
}
.nav-sidebar > .active > a,
.nav-sidebar > .active > a:hover,
.nav-sidebar > .active > a:focus {
  color: #fff;
  background-color: #428bca;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
			
				<li <c:if test="${empty softid}">class="active"</c:if>><a href="/config">基本设置</a></li>
			</ul>
			<ul class="nav nav-sidebar">
				<c:forEach items="${softs}" var="soft">
					<li <c:if test="${softid eq soft.id}">class="active"</c:if>>
						<a href="/config/${soft.id}">${soft.softname}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-sm-7 col-sm-offset-3 col-md-8 col-md-offset-2 main">
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		var softid="${softid}";
		if(""==softid){
			$(".main").load("/config/sys.form");
		}else{
			$(".main").load("/config/soft.form",{softid:softid});
		}
	});
	</script>
</body>
</html>



