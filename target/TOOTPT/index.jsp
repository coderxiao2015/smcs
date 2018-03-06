<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <script src="./js/jquery.min.js"></script>
</head>
<body>
<a href="/open/login">用户登录</a>
<a href="/open/adduser">添加用户</a>
<a href="/user/userInfo">查看用户信息</a>

<input type="button" value="测试登录" onclick="loginTest()">
<a href="/open/usercenter">测试个人中心</a>
</body>
<script type="application/javascript">
    function loginTest(){
      $.ajax({
          url:"/login/loginTest",
          async:true,
          type:'get',
          data:{"name":"zhangsan","password":"123456"},
          dataType:'json',
          success:function(data,textStatus,jqXHR){
              alert("登录成功");
              console.info(data);
          },
          error:function(){
              alert("登录失败！");
          }
      })
    }
</script>


</html>
