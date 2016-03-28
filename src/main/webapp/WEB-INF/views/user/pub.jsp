<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html>
<head>
<title>添加公用账号</title>
</head>
<body>

<div class="form-horizontal">
	<form id="addform">
		<div class="form-group">
			<label for="inputsoft" class="form-label col-xs-3 sr-only"></label>
			<div class="col-xs-3">
				<select id="inputsoft" class="form-control" name="softid" >
					<option value=''>请选择</option>
					<c:forEach items="${softs}" var="soft">
					<option value="${soft.id}">${soft.softname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputusername" class="col-xs-3 form-label sr-only">用户名</label>
	        <div class="col-xs-3">
				<input type="text" id="inputusername" name="username" class="form-control" placeholder="用户名">
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputpassword" class="col-xs-3 form-label sr-only">用户名</label>
	        <div class="col-xs-3">
				<input type="password" id="inputpassword" name="password" class="form-control" placeholder="密码">
			</div>
		</div>
		
	    <div class="form-group">
	        <div class="col-xs-3">
	            <button type="submit" class="btn btn-default">创建</button>
	        </div>
	    </div>
	</form>
</div>

<div id="cdkeycontent" class="form-horizontal">
	
</div>

<script src="//cdn.bootcss.com/epiceditor/0.2.2/js/epiceditor.min.js"></script>
<script type="text/javascript">
$(function(){

	/* 表单校验 */
    $('#addform').find('#inputsoft').multiselect().end()
    .formValidation({
        framework: 'bootstrap',
        icon: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	softid: {
        		excluded:false,
                validators: {
                	notEmpty:{
            			message:"请选择软件"
            		},
            		regexp:{
            			regexp:/^\d*$/,
            			message:"请选择软件"
            		}
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    }
                }
            },
            password:{
            	validators:{
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
