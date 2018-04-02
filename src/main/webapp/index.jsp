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

<<<<<<< HEAD
</tr>
</table>
=======
<input type="button" value="测试登录" onclick="loginTest()">
<a href="/open/usercenter">测试个人中心</a>

<a href="/share/shareHome?mid=63202">分享赚钱</a>

<a href="/user/toCouponList?mid=63202">优惠券</a>

<a href="/share/testRedis">测试redis缓存</a>

<a href="/share/insterInto">测试插入</a>


<input type="button" value="记录佣金生成轨迹" onclick="testCom()">
>>>>>>> 9d45c24a6672ea5961e31ad1322e1f7519783211

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
