package io.batcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopifyController {


    @GetMapping(value = "/testShopify")
    public String testShopify(HttpServletRequest request){
        String hmac = request.getParameter("hmac");
        System.out.println(hmac);
        return "";
    }
}
