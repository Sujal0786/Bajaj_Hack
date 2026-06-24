# 🚀 Bajaj Health (BFHL) Spring Boot Developer Challenge API

[![Java CI with Maven](https://github.com/Sujal0786/Bajaj_Hack/actions/workflows/maven.yml/badge.svg)](https://github.com/Sujal0786/Bajaj_Hack/actions)
[![Java Version](https://img.shields.io/badge/Java-17%20%2F%2021-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)

A highly optimized, production-ready REST API implementing the Bajaj Finserv Health Limited (BFHL) developer challenge. It processes input lists of items, extracts odd/even numbers, calculates mathematical sums securely using arbitrary-precision arithmetic, converts and filters alphabet sets, and constructs custom reversed-alternating-caps string sequences.

---

## 🛠️ Architecture & Tech Stack

This project follows a clean **N-Tier Layered Architecture** adhering to enterprise Java coding conventions:
- **Presentation Layer (`Controller`)**: Maps REST request inputs and enforces HTTP response specifications.
- **Data Transfer Object Layer (`DTO`)**: Implements immutable Java 17+ **Record Classes** with JSON mappings (`Jackson`) for optimal performance and cleaner code.
- **Service Layer (`Interface + Impl`)**: Decouples business rules and sorting/formatting algorithms from controllers.
- **Exception Layer (`Global Handler`)**: Standardizes client-facing exceptions (such as invalid JSON formats) into structured error payloads.

---

## 📂 Project Structure

```text
Bajaj_Hack/
│
├── .github/workflows/
│   └── maven.yml               # GitHub Actions CI Workflow
│
├── src/
│   ├── main/
│   │   ├── java/com/example/bfhl/
│   │   │   ├── BfhlApplication.java
│   │   │   ├── controller/
│   │   │   │   └── BfhlController.java
│   │   │   ├── dto/
│   │   │   │   ├── BfhlRequest.java
│   │   │   │   └── BfhlResponse.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── service/
│   │   │       ├── BfhlService.java
│   │   │       └── BfhlServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/bfhl/
│           └── controller/
│               └── BfhlControllerTest.java
│
├── Dockerfile                   # Multi-stage optimized Docker build
├── pom.xml                      # Maven Build Configuration
└── README.md                    # Documentation
```

---

## ⚙️ Core Logic Rules

1. **Numeric Filtering**: Pure numeric digit arrays (`^-?\d+$`) are extracted. Numbers are evaluated as even/odd using `BigInteger` to support very large numbers and prevent overflow.
2. **Alphabet Filtering**: Strings consisting entirely of characters (`^[a-zA-Z]+$`) are converted to uppercase.
3. **Special Characters**: All entries containing non-alphanumeric formats are categorized under `special_characters`.
4. **Reversed Alternating Concatenation (`concat_string`)**:
   - Gathers all alphabetical characters in their original order.
   - Reverses the combined string.
   - Converts index 0 to uppercase, index 1 to lowercase, index 2 to uppercase, and so on.

---

## 📡 API Endpoints

### 1. `POST /bfhl`
Processes the payload array.

- **Request Headers**: `Content-Type: application/json`
- **Request Body Format**:
  ```json
  {
    "data": ["a", "1", "334", "4", "R", "$"]
  }
  ```
- **Response Format (HTTP 200)**:
  ```json
  {
    "is_success": true,
    "user_id": "sujal_arora_17091999",
    "email": "sujal6067@gmail.com",
    "roll_number": "2310991299",
    "odd_numbers": ["1"],
    "even_numbers": ["334", "4"],
    "alphabets": ["A", "R"],
    "special_characters": ["$"],
    "sum": "339",
    "concat_string": "Ra"
  }
  ```

---

### 2. `GET /bfhl`
Used as a health check and validation hook by the challenge portal.

- **Response Format (HTTP 200)**:
  ```json
  {
    "operation_code": 1
  }
  ```

---

## 🧪 Running Tests & Local Verification

Build and run the JUnit 5 test suite locally:
```bash
mvn clean test
```

Start the application on port `8080` (or dynamic port via `PORT` variable):
```bash
mvn spring-boot:run
```

Test it locally using PowerShell:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/bfhl -Method Post -ContentType 'application/json' -Body '{"data": ["a", "1", "334"]}' | ConvertTo-Json
```

---

## ☁️ Deployment Instructions

### One-Click Deploy to Railway (Recommended)
1. Log in to [Railway.app](https://railway.app/).
2. Select **New Project** -> **Deploy from GitHub repository**.
3. Choose the **Sujal0786/Bajaj_Hack** repository.
4. Click **Deploy**.
5. Once complete, click **Generate Domain** in the service settings to get your public API URL.
