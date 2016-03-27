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
        	softid: {
                validators: {
                    notEmpty: {
                        message: '请输入软件id'
                    }
                }
            },
        	keyname: {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            },
        	prefix: {
                validators: {
                    notEmpty: {
                        message: '请输入前缀'
                    },
            		regexp:{
            			regexp:/^[0-9a-zA-Z]{4}$/,
            			message:"请输入4位前缀"
            		}
                }
        	},
            cday: {
                validators: {
                    notEmpty: {
                        message: '请输入充值时间'
                    },
            		regexp:{
            			regexp:/^[0-9]+(\.[0-9]{1,2})?$/,
            			message:"请输入时间,最多两位小数"
            		}
                }
            },
        	retailprice: {
                validators: {
                    notEmpty: {
                        message: '请输入零售价'
                    },
            		regexp:{
            			regexp:/^[0-9]+(\.[0-9]{1,2})?$/,
            			message:"请输入零售价,最多两位小数"
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
        	keysetid: {
                validators: {
                    notEmpty: {
                        message: '没有发现id'
                    }
                }
            },
            cday: {
                validators: {
                    notEmpty: {
                        message: '请输入充值时间'
                    },
            		regexp:{
            			regexp:/^[0-9]+(\.[0-9]{1,2})?$/,
            			message:"请输入时间,最多两位小数"
            		}
                }
            },
        	retailprice: {
                validators: {
                    notEmpty: {
                        message: '请输入零售价'
                    },
            		regexp:{
            			regexp:/^[0-9]+(\.[0-9]{1,2})?$/,
            			message:"请输入零售价,最多两位小数"
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
                url: "/keyset/add.json",
                data: $('#addform').serialize(),
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        button.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	var keyset = result["keyset"];
                    	$("#keysettable tbody").append(
                    			'<tr><td><input type="checkbox" /></td>'
                    			+'<td>'+keyset["id"]+'</td>'
                    			+'<td>'+keyset["softid"]+'</td>'
                    			+'<td>'+keyset["keyname"]+'</td>'
                    			+'<td>'+keyset["cday"]+'</td>'
                    			+'<td>'+keyset["prefix"]+'</td>'
                    			+'<td>'+keyset["retailprice"]+'</td>'
                    			+'<td>'
                    			+'<a data-action="edit" data-toggle="modal" data-target="#editModal" href="#" title="编辑"><span class="glyphicon glyphicon-edit" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'<a data-action="del" data-toggle="modal" data-target="#delselModal" href="#" title="删除"><span class="glyphicon glyphicon-remove" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'<a data-action="lock" data-toggle="modal" href="#" title="锁定"><span class="glyphicon glyphicon-ban-circle" tabindex="0" role="button" data-trigger="focus"></span></a>'
                    			+'</td></tr>');
                    	//关闭
                    	$('#addModal').modal('hide');
                    	
                    }
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
	$('#editModal').on('hide.bs.modal', function(event) {
		$('#editform').data('formValidation').resetForm();
	}).on('show.bs.modal', function(event) {
		edititem = $(event.relatedTarget).closest("tr"); // Button that triggered the modal
		var keysetid = edititem.children("td:eq(1)").html();
		//提交表单
        $.ajax({
            type: 'POST',
            url: "/keyset/detail.json",
            data: {keysetid:keysetid},
            dataType:"json",
            success: function(result){
                if(true == result["hasError"]){
                    //提示错误
                	$('#editModal').modal('hide');
                    button.popover({content:"错误:"+result["error"]}).popover('show');
                }else if(true == result["success"]){
                	var keyset = result["keyset"];
                	$('#editform #inputcday').val(keyset["cday"]);
                	$('#editform #inputkeysetid').val(keyset["id"]);
                	$('#editform #inputretailprice').val(keyset["retailprice"]);
                }
            }
        });
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
                url: "/keyset/update.json",
                data: $('#editform').serialize(),
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        button.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	var keyset = result["keyset"];
                    	$(edititem).children("td:eq(1)").html(keyset["id"]);
                    	$(edititem).children("td:eq(2)").html(keyset["softid"]);
                    	$(edititem).children("td:eq(3)").html(keyset["keyname"]);
                    	$(edititem).children("td:eq(4)").html(keyset["cday"]);
                    	$(edititem).children("td:eq(5)").html(keyset["prefix"]);
                    	$(edititem).children("td:eq(6)").html(keyset["retailprice"]);
                    	//关闭
                    	$('#editModal').modal('hide');
                    }
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
			$("#delcontent").html("确定删除"+delitems.children("td:eq(3)").html()+"么?");
		}else{
			delitems = $("#keysettable tbody :checked").closest("tr");
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
            url: "/keyset/del.json",
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
                }
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

        function dolock(keysetid){
            $.ajax({
                type: 'POST',
                url: "/keyset/lock.json",
                data: {keysetid:keysetid},
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        span.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	item.attr("title","解锁");
                    	span.removeClass("glyphicon-ban-circle").addClass("glyphicon-ok-circle");
                    	span.popover({content:"成功锁定"}).popover('show');
                    }
                }
            });
        }

        function unlock(keysetid){
            $.ajax({
                type: 'POST',
                url: "/keyset/unlock.json",
                data: {keysetid:keysetid},
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        span.popover({content:"错误"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	item.attr("title","锁定");
                    	span.removeClass("glyphicon-ok-circle").addClass("glyphicon-ban-circle");
                    	span.popover({content:"成功解锁"}).popover('show');
                    }
                }
            });
        }
        
    });
});