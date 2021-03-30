package com.arjunkoottalasajayan.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/blogServiceFallback")
    public String blogServiceFallbackMethod(){
        return "Blog service takes longer time than expected";
    }

    @GetMapping("/commentServiceFallback")
    public String commentServiceFallbackMethod(){
        return "Comment service takes longer time than expected";
    }
}
