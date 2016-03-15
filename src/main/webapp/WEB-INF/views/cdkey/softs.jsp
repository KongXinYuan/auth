<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<div class="container-fluid">
	<div class="row">
		<c:forEach items="${softs}" var="soft">
		<div class="col-md-3">
			<a class="btn btn-default" href="/cdkey/list/${soft.id}" role="button">${soft.softname}</a>
		</div>
		</c:forEach>
	</div>
</div>