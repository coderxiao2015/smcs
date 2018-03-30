import org.junit.Test;

public class Parent {

    private final String aa="3";


    public final void  getAa(){
        System.out.println(aa);
    }

    class Child extends Parent{


        public void getbb(){
            super.getAa();
        }

    }
}
