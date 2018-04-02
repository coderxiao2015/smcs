<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <script src="./js/jquery.min.js"></script>
    <script src="${basePath}/js/jquery.min.js"></script>
</head>
<body>
<table>
<tr>
    <td><a href="/my/aop">AOP测试</a></td>
    <td><a href="/share/testRedis">redis测试</a></td>

</tr>
</table>

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
    };


</script>


</html>
