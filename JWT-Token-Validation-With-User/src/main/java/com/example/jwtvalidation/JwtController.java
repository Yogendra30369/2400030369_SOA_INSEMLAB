package com.example.jwtvalidation;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validate(@RequestBody TokenRequest request) {
        try {
            Map<String, Object> claims = jwtService.validateToken(request.getToken());
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("message", "Token Valid");
            response.put("claims", claims);
            return ResponseEntity.ok(response);
        } catch (JwtException | IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid Token"));
        }
    }

    public static class TokenRequest {
        private String token;

        public TokenRequest() {
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
