package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/api/name")
    public String main() {
        System.out.printf("Yash");
        return "Gaurav";
    }




}
