<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#userForm").submit(function(){
			var username = $("#username");
			var status = $("#status");
			var loginname = $("#loginname");
			var password = $("#password");
			var lname = $("#lname");
			var msg = "";
			if ($.trim(username.val()) == ""){
				msg = "姓名不能为空！";
				username.focus();
			}else if ($.trim(loginname.val()) == ""){
				msg = "登录名不能为空！";
				loginname.focus();
			}else if (lname.text().length>5){
				msg = "用户名不可用！";
				loginname.focus();
			}else if ($.trim(password.val()) == ""){
				msg = "密码不能为空！";
				password.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#userForm").submit();
		});
    	$("#loginname").blur(function(){
    		$.ajax({
    			url:"${ctx}/user/checkName.action",
    			type:"get",
    			data:{"loginname":$(this).val()},
    			dataType:"text",
    			success:function(data){
    				$("#lname").html("<span style='color:red'>"+data+"</span>");
    			},
    			error:function(){
    				alert("执行异常!");
    			}
    		})
    	})
    });
		
	
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理  &gt; 编辑/添加用户</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<form action="${ctx}/user/useraddsave.action" id="userForm" method="post">
<table width="100%"  border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr><td>${message}</td></tr>
  <tr valign="top">
    <td>
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<input type="hidden" name="id" value="${user.id }">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓&nbsp;名：<input type="text" name="username" id="username" size="20" value="${user.username }"/></td>
		    			<td class="font3 fftd">状&nbsp;态：<select name="status">
														<c:choose>
															<c:when test="${user.status==2 }">
																<option value="1">未审核</option>
																<option value="2" selected="selected">已审核</option>
															</c:when>
															<c:otherwise>
																<option value="1">未审核</option>
																<option value="2">已审核</option>
															</c:otherwise>
														</c:choose>
														
													</select></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd" colspan="2"><label id="lname"></label></td>
		    		</tr>	
		    		<tr>
		    			<td class="font3 fftd">登录名：<input name="loginname" id="loginname" size="20" value="${user.loginname }"/></td>
		    			<td class="font3 fftd">密&nbsp;码：<input name="password" id="password" size="20" value="${user.password }"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td align="left" class="fftd">
				<c:choose>
					<c:when test="${val=='add'}">
						<input type="submit" value="添加">
					</c:when>
					<c:otherwise>	
						<input type="submit" value="编辑">
					</c:otherwise>
				</c:choose>
			&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
	</td>
  </tr>
</table>
</form>
</body>
</html>