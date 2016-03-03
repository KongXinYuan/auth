<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>灵犀管理系统</title>
		<script src="${ctx}/static/js/jquery-1.8.1.min.js" type="text/javascript"></script>
		<link href="${ctx}/static/css/dpl-min.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/css/bui-min.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/css/main-min.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/js/jquery-1.8.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/static/js/bui.js" type="text/javascript"></script>
		<script src="${ctx}/static/js/config.js" type="text/javascript"></script>
	</head>

  <div class="header">
      <div class="dl-title">
        <a href="${ctx}" target="_blank">
          <span class="lp-title-port">灵犀</span><span class="dl-title-text">管理系统</span>
        </a>
      </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${admin}</span><a href="${ctx}/logout" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title">贴心小秘书<s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
        <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">首页</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-permission">管理员</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-user">用户</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-monitor">POST</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-package">文档</div></li>
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
   </div>

  <script>
    BUI.use('common/main',function(){
      var config = [{
          id:'home', 
          homePage : 'welcome',
          menu:[{
              text:'首页',
              items:[
                {id:'welcome',text:'欢迎页面',href:'${ctx}/home/welcome',closeable : false}
              ]
            }]
          },{
            id:'admin',
            menu:[{
                text:'管理员',
                items:[
                  {id:'detail',text:'个人资料',href:'${ctx}/admin/detail'},
                  {id:'list',text:'管理员列表',href:'${ctx}/admin/list'}
                ]
              },{
                text:'超级管理员',
                items:[
                  {id:'administrator',text:'超级管理员资料',href:'${ctx}/admin/detail/1'}
                ]
              }]
          },{
            id:'user',
            menu:[{
                text:'用户管理',
                items:[
                  {id:'list',text:'用户列表',href:'${ctx}/user/list'}
                ]
              }]
          },{
            id:'post',
            menu:[{
                text:'帖子管理',
                items:[
                  {id:'list',text:'帖子',href:'${ctx}/admin/post'}
                ]
              }]
          },{
              id:'doc',
              menu:[{
                  text:'文档',
                  items:[
                    {id:'api',text:'api',href:'${ctx}/admin/api'}
                  ]
                },{
					text:'测试',
                    items:[
                      {id:'api',text:'api',href:'${ctx}/admin/api'}
                    ]
                  }]
            }];
      
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>
 </body>
</html>



