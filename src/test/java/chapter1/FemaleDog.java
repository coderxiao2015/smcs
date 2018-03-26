package chapter1;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import org.junit.Test;

public class FemaleDog extends Dog {

    @Override
    public void eat(String name, String food) {
        System.out.println("母"+name+"吃"+food);
    }

    @Test
    public void test(){
        Animal animal=new FemaleDog();
        animal.eat("zhangsan","huasheng");
    }
}
