package com.fh.shop.admin.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
@RequestMapping(value="/index")
    public String toIndex(){
        return "index";
    }

}
