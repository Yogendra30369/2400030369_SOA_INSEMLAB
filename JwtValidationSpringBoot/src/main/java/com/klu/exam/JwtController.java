package com.klu.exam;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {
    private final JwtService service;

    public JwtController(JwtService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        if ("user".equals(user.getUsername())
                && "1234".equals(user.getPassword())) {
            User loggedInUser = new User("user", "", "USER");
            return Map.of("token", service.generateToken(loggedInUser));
        }

        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }

    @PostMapping("/validate")
    public Map<String, Object> validate(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        if (token == null || token.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Token is required");
        }

        try {
            return Map.of(
                "message", "Token Valid",
                "claims", service.validateToken(token)
            );
        } catch (JwtException | IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid Token");
        }
    }
}
