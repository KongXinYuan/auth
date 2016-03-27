$(function() {
	
	/* 表单校验 */
    $('#searchForm').formValidation({
        framework: 'bootstrap',
        icon: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            adminid: {
                validators: {
            		regexp:{
            			regexp:/^\d*$/,
            			message:"adminid不是正整数"
            		}
                }
            },
        	username: {
                validators: {
            		regexp:{
            			regexp:/^.{2,50}$/,
            			message:"用户名长度在2-50之间"
            		}
                }
            },
            cdkey: {
                validators: {
            		regexp:{
            			regexp:/^[0-9][a-z][A-Z]{32}$/,
            			message:"32位字母或小数"
            		}
                }
            },
            tag: {
                validators: {
            		regexp:{
            			regexp:/^.{2,20}$/,
            			message:"长度2-20直接"
            		}
                }
            },
            pub: {
                validators: {
            		regexp:{
            			regexp:/^\0|[0,1]$/,
            			message:"公用为空或0,1"
            		}
                }
            },
            lock: {
                validators: {
            		regexp:{
            			regexp:/^\0|[0,1]$/,
            			message:"锁定为空或0,1"
            		}
                }
            }
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
			delitems = $("#cdkeytable tbody :checked").closest("tr");
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
       	if(delids.length == 0){
       		$('#delselModal').modal('hide');
       		return;
       	}
    	
        $.ajax({
            type: 'POST',
            url: "/user/del.json",
            data: {softid:softid,delids:delids},
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
            }
        });
        
    });
    

	
    //锁定或者解锁
    $("[data-action='lock'],[data-action='locksel'],[data-action='unlocksel']").click(function () {
    	//触发对象
    	var item = $(this);
    	//操作对象
    	var lockitems;
    	item.popover('destroy');
    	//是否锁定
    	var islock;
    	if("lock"==item.data("action")){
    		lockitems=item.closest("tr");
    		if("锁定" == item.attr("title")){
    			islock=true;
            }else{
            	islock=false;
            }
    	}else{
    		lockitems=$("#cdkeytable tbody :checked").closest("tr");
    		if("locksel"==item.data("action")){
    			islock=true;
    		}else{
    			islock=false;
    		}
    	}
    	
    	//ids
    	var ids=[];
    	lockitems.each(function(){
    		var id=$(this).children("td:eq(1)").html();
    		ids.push(id);
    	});
    	if(ids.length > 0){
        	if(islock){
        		dolock(ids);
        	}else{
        		unlock(ids);
        	}
    	}

        function dolock(lockids){
            $.ajax({
                type: 'POST',
                url: "/user/lock.json",
                data: {softid:softid, lockids:lockids},
                dataType:"json",
                success: function(result){
                	var span = item.children("span");
                    if(true == result["hasError"]){
                        //提示错误
                    	span.popover({content:"错误:"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	lockitems.find(".glyphicon-ban-circle").removeClass("glyphicon-ban-circle").addClass("glyphicon-ok-circle").closest("span").attr("title","解锁");
                    	span.popover({content:"成功锁定"}).popover('show');
                    }else{
                    	span.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                }
            });
        }

        function unlock(lockids){
            $.ajax({
                type: 'POST',
                url: "/user/unlock.json",
                data: {softid:softid, lockids:lockids},
                dataType:"json",
                success: function(result){
                	var span = item.children("span");
                    if(true == result["hasError"]){
                        //提示错误
                    	span.popover({content:"错误"+result["error"]}).popover('show');
                    }else if(true == result["success"]){
                    	lockitems.find(".glyphicon-ok-circle").removeClass("glyphicon-ok-circle").addClass("glyphicon-ban-circle").closest("span").attr("title","锁定");
                    	span.popover({content:"成功解锁"}).popover('show');
                    }else{
                    	span.popover({content:"未知数据格式:"+data}).popover('show');
                    }
                }
            });
        }
        
    });
});