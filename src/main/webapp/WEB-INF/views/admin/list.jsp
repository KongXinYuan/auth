<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>list</title>
</head>
<body>
	<div class="row">
		<form:form id="searchForm" class="form-horizontal span24">
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">管理员id：</label>
					<div class="controls">
						<input type="text" class="control-text" name="adminId">
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">用户名：</label>
					<div class="controls">
						<input type="text" class="control-text" name="username">
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">昵称：</label>
					<div class="controls">
						<input type="text" class="control-text" name="nickname">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span9">
					<label class="control-label">创建时间：</label>
					<div class="controls">
						<input type="text" class=" calendar" name="gmtCreatedBegin">
						<span>-</span>
						<input type="text" class=" calendar" name="gmtCreatedEnd">
					</div>
				</div>
				<div class="span3 offset2">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</div>
			</div>
		</form:form>
	</div>
	<div class="search-grid-container">
		<div id="grid"></div>
	</div>

	<div id="contentAdd" class="hide">
		<form id="J_Form" class="form-horizontal">
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">用户名</label>
					<div class="controls">
						<input name="username" type="text" data-rules="{required:true}"
							class="input-normal control-text">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">邮箱</label>
					<div class="controls">
						<input name="email" type="text" data-rules="{required:true,name:email}"
							class="input-normal control-text">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">昵称</label>
					<div class="controls">
						<input name="nickname" type="text" data-rules="{required:true}"
							class="input-normal control-text">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">密码</label>
					<div class="controls">
						<input id="password" name="password" type="password" data-rules="{required:true}"
							class="input-normal control-text">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">确认密码</label>
					<div class="controls">
						<input type="password" data-rules="{required:true,equalTo:'#password'}"
							class="input-normal control-text">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div id="contentEdit" class="hide">
		<form id="J_Form" class="form-horizontal">
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">用户名</label>
					<div class="controls">
						<input name="username" type="text" readonly="readonly"
							class="input-normal control-text" >
					</div>
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					<label class="control-label">昵称</label>
					<div class="controls">
						<input name="nickname" type="text" readonly="readonly"
							class="input-normal control-text">
					</div>
				</div>
			</div>

			<div class="control-group span8">
				<label class="control-label">状态</label>
				<div class="controls">
					<select name="status"
						class="input-normal">
						<option value="">请选择</option>
						<option value="0">激活</option>
						<option value="1">禁用</option>
					</select>
				</div>
			</div>
		</form>
	</div>


<script type="text/javascript">
  BUI.use('common/search',function (Search) {
    
    var editing = new BUI.Grid.Plugins.DialogEditing({
        contentId : 'contentEdit',
        autoSave : true
      }),
      
      adding = new BUI.Grid.Plugins.DialogEditing({
          contentId : 'contentAdd',
          autoSave : true
        }),
        
      columns = [
          {title:'id',dataIndex:'id',width:80,renderer:function(v){
            return Search.createLink({
              id : 'detail' + v,
              title : '管理员详情',
              text : v,
              href : '${ctx}/admin/detail/'+v
            });
          }},
          {title:'创建时间',dataIndex:'gmtCreated',width:100},
          {title:'上次登录时间',dataIndex:'gmtLastLogin',width:100},
          {title:'用户名',dataIndex:'username',width:100},
          {title:'昵称',dataIndex:'nickname',width:100},
          {title:'手机',dataIndex:'tel',width:100},
          {title:'Email',dataIndex:'email',width:100},
          {title:'角色',dataIndex:'role',width:100},
          {title:'状态',dataIndex:'status',width:100},
          {title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
            var editStr = '<span class="grid-command btn-edit" title="编辑管理员信息">编辑</span>',
              delStr = '<span class="grid-command btn-del" title="删除学生信息">删除</span>';//页面操作不需要使用Search.createLink
            return editStr + delStr;
          }}
        ],
      store = Search.createStore('${ctx}/admin/page.json',{
        proxy : {
          save : { //也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
            addUrl : '${ctx}/admin/doAdd.json',
            updateUrl : '${ctx}/admin/doEdit.json',
            removeUrl : '${ctx}/admin/doDel.json'
          },
          method : 'POST'
        },
        autoSync : true //保存数据后，自动更新
      }),
      gridCfg = Search.createGridCfg(columns,{
        tbar : {
          items : [
            {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:addFunction},
            {text : '<i class="icon-remove"></i>删除',btnCls : 'button button-small',handler : delFunction}
          ]
        },
        plugins : [editing,adding,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
      });

    var  search = new Search({
        store : store,
        gridCfg : gridCfg
      }),
      grid = search.get('grid');

    //增加操作
    function addFunction(){
    	adding.add({});
    }

    //删除操作
    function delFunction(){
      var selections = grid.getSelection();
      delItems(selections);
    }

    function delItems(items){
      var ids = [];
      BUI.each(items,function(item){
        ids.push(item.id);
      });

      if(ids.length){
        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
          store.save('remove',{ids : ids});
        },'question');
      }
    }

    //监听事件
    grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-del')){
        //删除一条记录
        var record = ev.record;
        delItems([record]);
      }else if(sender.hasClass('btn-edit')){
          //编辑一条记录
          var record = ev.record;
          
          var tmp=new Object();
          tmp.id=record.id;
          tmp.username=record.username;
          tmp.email=record.email;
          tmp.nickname=record.nickname;
          tmp.tel=record.tel;
          
          editing.edit(tmp);
      }
    });
  });
</script>
</body>
</html>