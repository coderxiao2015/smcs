package controller;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestSingle {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init(){
        System.out.println("aa");
    }
}
