# RBAC using Spring Boot and Spring Security

## Requirements
- Java 17
- Maven 3.6+
- IntelliJ IDEA, Eclipse, or Spring Tool Suite

## Run in an IDE
1. Extract the ZIP file.
2. Open the extracted `RBAC-Spring-Boot` folder as a Maven project.
3. Wait for Maven dependencies to download.
4. Run `RbacDemoApplication.java`.
5. The application starts at `http://localhost:8080`.

## Test credentials
- Admin: username `admin`, password `admin123`
- User: username `user`, password `user123`

These in-memory credentials are for local demonstration only.

## Test with Postman
Use GET requests and select **Authorization > Basic Auth**.

1. `GET http://localhost:8080/api/admin/dashboard`
   - `admin / admin123` -> **200 OK**
   - `user / user123` -> **403 Forbidden**
   - no credentials -> **401 Unauthorized**

2. `GET http://localhost:8080/api/user/profile`
   - `user / user123` -> **200 OK**
   - `admin / admin123` -> **403 Forbidden**
   - no credentials -> **401 Unauthorized**

## Run from terminal
From the folder containing `pom.xml`:

```bash
mvn spring-boot:run
```

Run the automated authorization tests:

```bash
mvn test
```

## Authorization rules
- `/api/admin/**` requires role `ADMIN`.
- `/api/user/**` requires role `USER`.
- HTTP Basic authentication is enabled.
- CSRF is disabled for this REST API demonstration.
