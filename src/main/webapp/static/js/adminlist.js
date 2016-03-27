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
            username: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    }
                }
            },
            money:{
            	validators:{
                    notEmpty: {
                        message: '请输入金额'
                    },
            		regexp:{
            			regexp:/^[0-9]+(\.[0-9]{1,2})?$/,
            			message:"请输入金额"
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
                url: "/admin/add.json",
                data: $('#addform').serialize(),
                dataType:"json",
                success: function(result){
                    if(true == result["hasError"]){
                        //提示错误
                        button.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	var amdin = result["admin"];
                    	$("#admintable tbody").append(
                    			'<tr><td><input type="checkbox" /></td>'
                    			+'<td>'+amdin["id"]+'</td>'
                    			+'<td>'+amdin["role"]+'</td>'
                    			+'<td>'+amdin["username"]+'</td>'
                    			+'<td>'+amdin["parentname"]+'</td>'
                    			+'<td>'+amdin["addtime"]+'</td>'
                    			+'<td>'+amdin["lastlogintime"]+'</td>'
                    			+'<td>'+amdin["lastloginip"]+'</td>'
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
	
	
	
	
	/* 删除 标记当前删除的对象*/
	var delitems;
	$('#delselModal').on('show.bs.modal', function(event) {
		currentitem = $(event.relatedTarget) // Button that triggered the modal
		if(currentitem.data("action")=="del"){
			//当前行
			delitems = currentitem.closest("tr");
			$("#delcontent").html("确定删除"+delitems.children("td:eq(3)").html()+"么?");
		}else{
			delitems = $("#admintable tbody :checked").closest("tr");
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
            url: "http:/admin/del.json",
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

        function dolock(lockid){
            $.ajax({
                type: 'POST',
                url: "http:/admin/lock.json",
                data: {lockid:lockid},
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

        function unlock(lockid){
            $.ajax({
                type: 'POST',
                url: "http:/admin/unlock.json",
                data: {lockid:lockid},
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