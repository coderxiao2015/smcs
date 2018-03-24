import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = "prototype")
public class AnnotationTest {

    @Autowired
    private TestJedis testJedis;

    //method
    public void test(){
        //TODO somethine
    }
}
