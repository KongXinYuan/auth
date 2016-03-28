<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<form:form action="/config/${softid}" method="post" class="form-horizontal">
	<div class="form-group">
		<label class="control-label">公用账号时间</label>
		<input type="text" class="form-control" name="pubtime" value="${pubtime}" placeholder="公用账号时间"/>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-primary">保存</button>
	</div>
</form:form>