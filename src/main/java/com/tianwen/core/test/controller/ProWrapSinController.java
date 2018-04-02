package controller;/*测试多例模式下单例的状态*/


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope(value = "prototype")
@RequestMapping("/proWrapSin")
public class ProWrapSinController {

  /*  @Autowired
    private Single single;*/

    @RequestMapping(value = "/testSingle1")
    public void testSingle1(){
        System.out.println("aa");
        /*single.setName("zhangsan");
        System.out.println(single.getName());*/
    }


    @RequestMapping("/test2")
    public void testSingle2(){
       /* single.setName("wanwu");
        System.out.println(single.getName());*/
    }


}
