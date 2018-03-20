import com.google.gwt.thirdparty.guava.common.annotations.VisibleForTesting;
import com.tianwen.common.redisutil.RedisUtil;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

/*测试jedis*/
public class TestJedis {




    @Test
    public void testFinal(){
        final  int a;
        a=3;
        TestJedis testJedis=new TestJedis();
        final Student student=new Student();
        student.name="wang";
        System.out.println(student.getName());
        student.setName("zhansan");
        System.out.println(student.getName());
        student.setName("wang");
        System.out.println(student.getName());

    }

   final    class  Student{
        String name ;
        String sex;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    @Test
    public void testME(){
        String aa=(String)null;
        System.out.println(aa);
    }



@Test
    public void test2(){

}





}
