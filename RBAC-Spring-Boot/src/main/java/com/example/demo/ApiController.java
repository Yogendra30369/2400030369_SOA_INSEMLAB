package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin! You can access the management dashboard.";
    }

    @GetMapping("/api/user/profile")
    public String userProfile() {
        return "Welcome User! You can access your user profile.";
    }
}
