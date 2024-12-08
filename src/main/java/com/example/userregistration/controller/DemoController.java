package com.example.userregistration.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class DemoController {

    // Get all employees
    @GetMapping("/getall")
    public String getAllEmployees() {
        return "test";
    }

}
