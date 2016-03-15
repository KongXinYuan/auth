<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html>
<head>
<title>添加注册卡</title>
</head>
<body>

<div class="form-horizontal">

	<form id="addform">
		<div class="form-group">
			<label for="inputsoft" class="form-label col-xs-3 sr-only">软件</label>
			<div class="col-xs-3">
				<select id="inputsoft" class="form-control" name="softid" >
					<c:forEach items="${softs}" var="soft">
					<option value="${soft.id}">${soft.softname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
	    <div class="form-group">
	        <label for="inputkeyset" class="col-xs-3 form-label sr-only">卡类</label>
	        <div class="col-xs-3">
	            <select id="inputkeyset" class="form-control" name="keysetid">
	            </select>
	        </div>
	    </div>
		
		<div class="form-group">
			<label for="inputnum" class="col-xs-3 form-label sr-only">数量</label>
	        <div class="col-xs-3">
				<input type="text" id="inputnum" name="num" class="form-control" placeholder="数量">
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputtag" class="col-xs-3 form-label sr-only">tag</label>
	        <div class="col-xs-3">
				<input type="text" id="inputtag" name="tag" class="form-control" placeholder="tag">
			</div>
		</div>
	</form>
	
    <div class="form-group">
        <div class="col-xs-3">
            <button id="addsubmit" class="btn btn-default">确认</button>
        </div>
    </div>
</div>

<div id="cdkeycontent" class="form-horizontal">
	
</div>

<script src="//cdn.bootcss.com/epiceditor/0.2.2/js/epiceditor.min.js"></script>
<script type="text/javascript">
$(function(){

	/* 表单校验 */
    $('#addform').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	softid: {
                validators: {
                    notEmpty: {
                        message: '请选择软件'
                    },
                    integer:{
                    	message: '不是整数'
                    }
                }
            },
            keysetid: {
                validators: {
                    notEmpty: {
                        message: '请选择卡类'
                    },
                    integer:{
                    	message: '不是整数'
                    }
                }
            },
            num: {
                validators: {
                    notEmpty: {
                        message: '请输入num'
                    },
                    integer:{
                    	message: '不是整数'
                    }
                }
            },
            tag:{
            	validators:{
                    notEmpty: {
                        message: '请输入tag'
                    }
            	}
            }
        }
    });

    $("#inputtag").val(new Date().toLocaleDateString());
	$("#addsubmit").click(function(){
    	$('#addform').data('formValidation').validate();
    	if(true == $('#addform').data('formValidation').isValid()){
    		$.post("/cdkey/add.json",$('#addform').serialize(),function(result){
                if(true == result["hasError"]){
                    //提示错误
                    alert("错误:"+result["error"]);
                }else if(true == result["success"]){
        			var soft=$("#inputsoft option:selected").text();
        			var keyset=$("#inputkeyset option:selected").text();
        			var num=$("#inputnum").val();
        			var time=new Date().toISOString();
        			var editid=soft+'-'+keyset+'-'+num+'-'+time;
        			$("#cdkeycontent").append('<h4>'+soft+':'+keyset+':'+num+'</h4><div id="'+editid+'"></div>');
        			var opts = {
        					  container: editid,
        					  file:{
        						  defaultContent:result["cdkeys"]
        					  },
        					  theme:{
        						  base:"http://cdn.bootcss.com/epiceditor/0.2.2/themes/base/epiceditor.css",
        						  editor:"http://cdn.bootcss.com/epiceditor/0.2.2/themes/editor/epic-dark.css",
        						  preview:"http://cdn.bootcss.com/epiceditor/0.2.2/themes/preview/preview-dark.css"
        					  },
        					  button: false
        					}
        			var editor = new EpicEditor(opts).load();
                }
    		},"json");
    	}
		
	});
	
	
	$('#inputsoft').multiselect({
		nonSelectedText:"请选择",
		onChange: function(option, checked){
			if(checked){
				updateOption(option.val());
			}

		}
	});
	
	function updateOption(softid){
        $.ajax({
            type: 'POST',
            url: "/keyset/option.json",
            data: {softid:softid},
            dataType:"json",
            success: function(result){
                if(true == result["hasError"]){
                    //提示错误
                    alert("错误:"+result["error"]);
                }else if(true == result["success"]){
                	var keysets = result["keysets"];
                	//关闭
                	var options=[];
                	$.each(keysets,function(index,keyset) {
                		options.push({label: keyset.keyname, title: keyset.keyname, value: keyset.id});
                	});
                	
                	$('#inputkeyset').multiselect('dataprovider',options).multiselect('rebuild');
                }
            }
        });
	};
	
	updateOption($("#inputsoft option:selected").val());
	$('#inputkeyset').multiselect({
		nonSelectedText:"请选择"
	});
});
</script>


</body>
</html>
