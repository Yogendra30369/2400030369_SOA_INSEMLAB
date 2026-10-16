SOA Lab - Question 4: JWT Validation and Claims Extraction
Package: com.klu.exam
Java: 17
Spring Boot: 3.3.5

RUN:
1. Extract this ZIP.
2. Open the extracted JwtValidationSpringBoot folder in IntelliJ IDEA or Spring Tool Suite as a Maven project.
3. Wait for Maven dependencies to download.
4. Run src/main/java/com/klu/exam/JwtApplication.java.
5. App runs at http://localhost:8080.

POSTMAN TEST:
A) Login:
POST http://localhost:8080/api/jwt/login
Body > raw > JSON:
{
  "username": "user",
  "password": "1234"
}
Copy the token from the response.

B) Validate:
POST http://localhost:8080/api/jwt/validate
Body > raw > JSON:
{
  "token": "PASTE_COPIED_TOKEN_HERE"
}
Expected: message "Token Valid" and claims username=user, role=USER.

C) Invalid token:
Send an altered token to /api/jwt/validate.
Expected: HTTP 401 Unauthorized, "Invalid Token".

DEMO LOGIN:
username: user
password: 1234

Note: This is a simple lab demonstration with a hardcoded user and demo signing secret.
Do not use these demo credentials or secret in a real application.
