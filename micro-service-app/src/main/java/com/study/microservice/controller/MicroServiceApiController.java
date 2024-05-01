package com.study.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroServiceApiController {
    @GetMapping("/hello")
    public String helloGet() {
        return "Hello Get";
    }

    @GetMapping("/microservice-hello")
    public String microServiceHelloGet() {
        return "This is Micro Service Hello Get";
    }

    @PostMapping("/hello")
    public String helloPost() {
        return "Hello Post";
    }
}


