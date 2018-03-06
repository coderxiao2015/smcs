<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<form id="newmember">
	<input placeholder="账号" type="text" name="account">
	<input placeholder="密码" type="text" name="password">
	<input type="submit" value="确定">
</form>
</body>
<script src="/js/jquery.form.js"></script>
<script>
(function ($) {
	$("#newmember").sgfmform({
		ajaxurl     :"/open/addNewUser",
	 	tiptype 	: 1,
	 	submittype  : 2,
	 	callback    : function(data,url){
	 		if(data.returncode == 0){
	 			alert(1)
	 		}else{
	 			alert(2)
	 		}
	 	}
	});
})(jQuery);
</script>
</html>
