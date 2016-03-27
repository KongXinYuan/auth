$(function() {
	
	/* 表单校验 */
    $('#addform').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	softname: {
                validators: {
                    notEmpty: {
                        message: '请输入软件名'
                    }
                }
            },
            intervaltime: {
                validators: {
                	integer: {
                        message: '请输入intervaltime'
                    },
                    notEmpty: {
                        message: '请输入intervaltime'
                    }
                }
            },
            privkey:{
            	validators:{
            		base64: {
                        message: '不是base64格式'
                    },
                    notEmpty: {
                        message: '请输入privkey'
                    }
            	}
            }
        }
    });

    $('#editform').formValidation({
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
                        message: '没有发现id'
                    }
                }
            },
            intervaltime: {
                validators: {
                	integer: {
                        message: '不是数字'
                    },
                    notEmpty: {
                        message: '请输入intervaltime'
                    }
                }
            },
            privkey:{
            	validators:{
            		base64: {
                        message: '不是base64格式'
                    }
            	}
            }
        }
    });
    
    
	/* 添加 */
    $("[data-action='add']").click(function () {
    	var button = $(this);
       	button.popover('destroy');
    	$('#addform').data('formValidation').validate();
    	if(true == $('#addform').data('formValidation').isValid()){
    		//提交表单
            $.ajax({
                type: 'POST',
                url: "/soft/add.json",
                data: $('#addform').serialize(),
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        button.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	var soft = result["soft"];
                    	$("#softtable tbody").append(
                    			'<tr><td><input type="checkbox" /></td>'
                    			+'<td>'+soft["id"]+'</td>'
                    			+'<td>'+soft["softcode"]+'</td>'
                    			+'<td>'+soft["softkey"]+'</td>'
                    			+'<td>'+soft["softname"]+'</td>'
                    			+'<td>'+soft["intervaltime"]+'</td>'
                    			+'<td>'
                    			+'<a data-action="edit" data-toggle="modal" data-target="#editModal" href="#" title="编辑"><span class="glyphicon glyphicon-edit" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'<a data-action="del" data-toggle="modal" data-target="#delselModal" href="#" title="删除"><span class="glyphicon glyphicon-remove" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'<a data-action="lock" data-toggle="modal" href="#" title="锁定"><span class="glyphicon glyphicon-ban-circle" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'</td></tr>');
                    	//关闭
                    	$('#addModal').modal('hide');
                    	
                    }else{
                    	button.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                },
                error:function(data){
                    //提示错误
                    button.popover({content:"未知错误"}).popover('show');
                }
            });
    	}
    	
    });
    
    //重置valid
	$('#addModal').on('hide.bs.modal', function(event) {
		$('#addform').data('formValidation').resetForm();
	});
	
	//获取用户数据
	var edititem;
	$('#editModal').on('show.bs.modal', function(event) {
		edititem = $(event.relatedTarget).closest("tr"); // Button that triggered the modal
		var softid = edititem.children("td:eq(1)").html();
		//提交表单
        $.ajax({
            type: 'POST',
            url: "/soft/detail.json",
            data: {softid:softid},
            dataType:"json",
            success: function(result){
                if(true == result["hasError"]){
                    //提示错误
                	$('#editModal').modal('hide');
                    button.popover({content:"错误:"+result["error"]}).popover('show');
                }else if(true == result["success"]){
                	var soft = result["soft"];
                	$('#editform #inputsoftid').val(soft["id"]);
                	$('#editform #inputsoftname').val(soft["softname"]);
                	$('#editform #inputsoftcode').val(soft["softcode"]);
                	$('#editform #inputsoftkey').val(soft["softkey"]);
                	$('#editform #inputintervaltime').val(soft["intervaltime"]);
                }else{
                	$('#editModal').modal('hide');
                	button.popover({content:"未知数据格式:"+data}).popover('show');
                }
            },
            error:function(data){
                //提示错误
            	$('#editModal').modal('hide');
                button.popover({content:"未知错误"}).popover('show');
            }
        });
	}).on('hide.bs.modal', function(event) {
		$('#editform').data('formValidation').resetForm();
	});
	
	
    $("[data-action='genRsaKey']").click(function () {
    	var button = $(this);
    	$.get("/soft/rsakey.json",function(result){
            if(true == result["hasError"]){
                button.popover({content:"错误:"+result["error"]}).popover('show');
            }else if(true == result["success"]){
            	button.closest("form").find(".inputprivkey").val(result.privKey);
            	button.closest("form").find(".inputpubkey").val(result.pubKey);
            }
    	},"json");
    	
    });

	/* 编辑 */
    $("[data-action='update']").click(function () {
    	var button = $(this);
       	button.popover('destroy');
    	$('#editform').data('formValidation').validate();
    	if(true == $('#editform').data('formValidation').isValid()){
    		//提交表单
            $.ajax({
                type: 'POST',
                url: "/soft/update.json",
                data: $('#editform').serialize(),
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        button.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	var soft = result["soft"];
                    	$(edititem).children("td:eq(1)").html(soft["id"]);
                    	$(edititem).children("td:eq(2)").html(soft["softcode"]);
                    	$(edititem).children("td:eq(3)").html(soft["softkey"]);
                    	$(edititem).children("td:eq(4)").html(soft["softname"]);
                    	$(edititem).children("td:eq(5)").html(soft["intervaltime"]);
                    	//关闭
                    	$('#editModal').modal('hide');
                    	
                    }else{
                    	button.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                },
                error:function(data){
                    //提示错误
                    button.popover({content:"未知错误"}).popover('show');
                }
            });
    	}
    	
    });

    
	

	/* 删除 标记当前删除的对象*/
	var delitems;
	$('#delselModal').on('show.bs.modal', function(event) {
		currentitem = $(event.relatedTarget) // Button that triggered the modal
		if(currentitem.data("action")=="del"){
			//当前行
			delitems = currentitem.closest("tr");
			$("#delcontent").html("确定删除"+delitems.children("td:eq(4)").html()+"么?");
		}else{
			delitems = $("#softtable tbody :checked").closest("tr");
			$("#delcontent").html("确定删除全部所选么?");
		}
	});
	
	
    //删除
    $("[data-action='delsel']").click(function () {
    	
    	var delids = [];
    	delitems.each(function(){
    		delids.push($(this).children("td:eq(1)").html());
    	});
       	var button = $(this);
       	button.popover('destroy');
    	
        $.ajax({
            type: 'POST',
            url: "http:/soft/del.json",
            data: {delids:delids},
            dataType:"json",
            success: function(result){
                if(true == result["hasError"]){
                    //提示错误
                    button.popover({content:"错误:"+result["error"]}).popover('show');
                }else if(true == result["success"]){
                	delitems.remove();
                	//关闭
                	$('#delselModal').modal('hide');
                }else{
                	button.popover({content:"未知数据格式:"+data}).popover('show');
                }
            },
            error:function(data){
                //提示错误
                button.popover({content:"未知错误"}).popover('show');
            }
        });
        
    });
    
	
    //锁定或者解锁
    $("[data-action='lock']").click(function () {
    	var item = $(this);
    	var span = item.children("span");
    	span.popover('destroy');
        var id = item.closest("tr").children("td:eq(1)").html();
        if("锁定" == $(this).attr("title")){
            dolock(id);
        }else{
            unlock(id);
        }

        function dolock(softid){
            $.ajax({
                type: 'POST',
                url: "http:/soft/lock.json",
                data: {softid:softid},
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        span.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	item.attr("title","解锁");
                    	span.removeClass("glyphicon-ban-circle").addClass("glyphicon-ok-circle");
                    	span.popover({content:"成功锁定"}).popover('show');
                    }else{
                    	span.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                },
                error:function(data){
                    //提示错误
                    span.popover({content:"未知错误"}).popover('show');
                }
            });
        }

        function unlock(softid){
            $.ajax({
                type: 'POST',
                url: "http:/soft/unlock.json",
                data: {softid:softid},
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        span.popover({content:"错误"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	item.attr("title","锁定");
                    	span.removeClass("glyphicon-ok-circle").addClass("glyphicon-ban-circle");
                    	span.popover({content:"成功解锁"}).popover('show');
                    }else{
                    	span.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                },
                error:function(data){
                    //提示错误
                    span.popover({content:"未知错误"}).popover('show');
                }
            });
        }
        
    });
});