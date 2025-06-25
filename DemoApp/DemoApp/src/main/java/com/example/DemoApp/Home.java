package com.example.DemoApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class Home {

    public String show(){
        return "hello";
    }

}
