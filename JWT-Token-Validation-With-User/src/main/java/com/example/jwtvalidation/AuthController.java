package com.example.jwtvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        String role = getRole(user.getUsername(), user.getPassword());

        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid username or password"));
        }

        String token = jwtService.createToken(user.getUsername(), role);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Login successful");
        response.put("username", user.getUsername());
        response.put("role", role);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    private String getRole(String username, String password) {
        if ("user".equals(username) && "user123".equals(password)) {
            return "USER";
        }
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "ADMIN";
        }
        return null;
    }
}
