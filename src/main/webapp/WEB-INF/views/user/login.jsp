<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp"%>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>医保用户</title>
</head>
<body>
<form id="doLogin">
	<input placeholder="账号" type="text" name="account">
	<input placeholder="密码" type="text" name="password">
	<input type="submit" value="确定">
	<input type="button" onclick="self.location='/open/logout'"  value="退出"> 
</form>
</body>
<script src="/js/jquery.form.js"></script>
<script>
(function ($) {
	$("#doLogin").sgfmform({
		ajaxurl     :"/open/doLogin",
	 	tiptype 	: 1,
	 	submittype  : 2,
	 	callback    : function(data,url){
	 		if(data.returncode == 0){
	 			//console.info(data.data[0]);
	 			alert("登录成功！");
				setTimeout(function(){//登录返回
				window.location.href= data.data[0].back_url || "/";},1000);
	 		}else{
	 			alert("error");
	 		}
	 	}
	});
})(jQuery);
</script>
</html>
