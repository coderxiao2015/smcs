package chapter1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Dog implements Animal {

    @Override
    public void eat(String name, String food) {
        System.out.println(name+"正在吃"+food);
    }
}
