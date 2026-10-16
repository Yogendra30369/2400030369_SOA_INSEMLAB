# JWT Validation with User Login

## Requirements
Java 17 and Maven. Import this folder as an Existing Maven Project in STS/Eclipse, then run `JwtValidationApplication.java`.

## 1. Login and generate a token
POST `http://localhost:8080/api/auth/login`
Body > raw > JSON:
```json
{
  "username": "user",
  "password": "user123"
}
```
Demo admin credentials: `admin` / `admin123`.
Demo user credentials: `user` / `user123`.
The response includes a JWT token. Copy the token value.

## 2. Validate token
POST `http://localhost:8080/api/jwt/validate`
Header: `Content-Type: application/json`
Body > raw > JSON:
```json
{
  "token": "PASTE_TOKEN_FROM_LOGIN_HERE"
}
```
Valid token: HTTP 200 with `Token Valid`, username, and roles.
Altered, malformed, or expired token: HTTP 401 with `Invalid Token`.

## 3. Test altered token
Change a character in the copied token and submit it to `/api/jwt/validate`. It should be rejected.

## Note
This is a lab demonstration using hardcoded demo users. Do not use these credentials or the sample secret in production. Production applications should use a database, password hashing, and secret management.
