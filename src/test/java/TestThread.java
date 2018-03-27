import org.junit.Test;

public class TestThread {

    @Test
    public void test(){
        ThreadLocal<TestThread> aa=new ThreadLocal<>();

        System.out.println(aa.get());
        System.out.println(aa.get());
    }
}
