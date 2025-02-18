package com.kimh.spm;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {
    @GetMapping("/RestTest")
    public String home() {
        return "Hello, RestTest!";
    }
}