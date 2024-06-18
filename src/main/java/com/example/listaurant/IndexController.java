package com.example.listaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {

    @GetMapping("/")
    public String main(){
        return "index";
    }
    @GetMapping("/board")
    public String board(){
        return "board";
    }
}
