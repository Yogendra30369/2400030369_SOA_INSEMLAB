# API Request Validation using Spring Boot

## Requirements
- Java 17
- Maven 3.6+
- Spring Tool Suite (STS), IntelliJ IDEA, or Eclipse

## Run in STS / Eclipse
1. Extract the ZIP file.
2. Import the extracted folder using File > Import > Maven > Existing Maven Projects.
3. Select the folder containing `pom.xml` and finish the import.
4. Wait for Maven dependencies to download.
5. Run `OrderValidationApplication.java` as a Spring Boot App.
6. The API runs at `http://localhost:8080`.

## Test using Postman
Method: `POST`
URL: `http://localhost:8080/api/orders`
Header: `Content-Type: application/json`
Body > raw > JSON.

### Valid JSON (expected 200 OK)
```json
{
  "customerName": "Ravi",
  "productName": "Laptop",
  "quantity": 2,
  "price": 45000
}
```

### Invalid JSON (expected 400 Bad Request)
```json
{
  "customerName": " ",
  "productName": "",
  "quantity": 0,
  "price": -10
}
```

### Missing fields (expected 400 Bad Request)
```json
{
  "customerName": "Ravi",
  "productName": "Laptop"
}
```

## Validation rules
- Customer name and product name must not be blank.
- Quantity is required and must be greater than zero.
- Price is required and cannot be negative (zero is allowed).
- `@Valid` validates the request before the controller processes it.
- Validation errors return HTTP 400 with a JSON map of field names and messages.

## Run tests
Run `mvn test` from the folder containing `pom.xml`.
