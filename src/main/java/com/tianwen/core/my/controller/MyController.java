package com.tianwen.core.my.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/4/1.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/my")
public class MyController {

    /*分析aop切面编程*/
    @RequestMapping("/aop")
    public void testAop(){
        System.out.println("aop");
    }

}
