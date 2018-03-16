package com.tianwen.core.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianwen.common.shiro.token.ShiroToken;
import com.tianwen.common.util.JsonResponseResult;


/*测试登录权限*/
@Controller
@Scope("prototype")
@RequestMapping("/login")
public class TestLoginController {


 /*
 * 测试登录权限
 * @param name password
 *
 * */
    @GetMapping(value = "/loginTest")
    @ResponseBody
    public JsonResponseResult loginTest(HttpServletRequest request){
     JsonResponseResult result=null;
     try{
         String aa=request.getParameter("name");
         String name="zhangsan";
         String password="12346";
         //获取主题
         Subject subject= SecurityUtils.getSubject();
         ShiroToken token=new ShiroToken(name,password);
         subject.login(token);
         result=JsonResponseResult.createSuccess("登录成功"); //实际项目中真正的错误提示不应该提示是用户名错误还是密码错误，为了防止暴力攻击
     }catch (UnknownAccountException e) {
         result=JsonResponseResult.createFalied("用户名错误");
         System.out.println("用户名错误");
     } catch (IncorrectCredentialsException e) {
         result=JsonResponseResult.createFalied("密码错误");
          System.out.println("密码错误");
     } catch (AuthenticationException e) {
         e.printStackTrace();
         //其他错误，比如锁定，如果想单独处理请单独catch处理
        String  error = "其他错误：" + e.getMessage();
         result=JsonResponseResult.createFalied(error);
          System.out.println("其他错误");
     }catch (Exception e){
         e.printStackTrace();
         result=JsonResponseResult.createFalied("登录失败");
          System.out.println("登录失败");
     }
     return result;
 }

}
