# Chitkara BFHL Spring Boot API Qualifier

A Java Spring Boot REST API that processes input data arrays and extracts numbers (categorizing odd/even), alphabets (uppercasing), special characters, calculates the sum, and generates a reversed alternating caps string.

## Technical Specifications
- **Java**: 17
- **Spring Boot**: 3.3.0
- **Build Tool**: Maven
- **Port**: Configurable via `PORT` env variable (defaults to `8080`)

---

## Logic Highlights
1. **Separation of Numbers**: Pure numbers (`^\d+$`) are sorted into `even_numbers` or `odd_numbers`.
2. **Summation**: Parsed numbers are accumulated (BigInteger to avoid overflow) and returned as a string.
3. **Alphabet Mapping**: Pure alphabets (`^[a-zA-Z]+$`) are converted to uppercase and placed in `alphabets`.
4. **Special Characters**: Items that are neither pure numbers nor pure alphabets are classified under `special_characters`.
5. **Reversed Alternating Caps Concatenation**:
   - Collects alphabetical characters from alphabetic strings in the original order.
   - Reverses the combined string.
   - Applies alternating caps (Index 0 is uppercase, Index 1 is lowercase, etc.).

---

## Local Setup

### Prerequisites
- JDK 17 or higher
- Maven 3.6+

### Run the Application
Run the following command in the project root:
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

### Running Tests
Execute the JUnit test suite via:
```bash
mvn clean test
```

---

## API Documentation

### Route: `POST /bfhl`
Processes the request array.

#### Example Request
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

#### Example Response
```json
{
  "is_success": true,
  "user_id": "sujal_arora_17091999",
  "email": "sujal6067@gmail.com",
  "roll_number": "2110990000",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

---

## Customization & Deployment

To customize user credentials on deployment, configure these environment variables in your hosting provider:
- `APP_USER_NAME`: e.g. `sujal_arora`
- `APP_USER_DOB`: e.g. `17091999`
- `APP_USER_EMAIL`: e.g. `sujal6067@gmail.com`
- `APP_USER_ROLL_NUMBER`: Your actual Roll Number

### Deploy to Railway
1. Sign in to [Railway.app](https://railway.app/).
2. Click **New Project** -> **Deploy from GitHub repository**.
3. Select your repository.
4. Railway will auto-detect the `Dockerfile` and build it.
5. In **Variables**, add the custom credentials (like `APP_USER_ROLL_NUMBER` or `APP_USER_DOB`) if desired.
6. Enable a public domain in the service settings.

### Deploy to Render
1. Sign in to [Render.com](https://render.com/).
2. Click **New** -> **Web Service**.
3. Connect your GitHub repository.
4. Select `Docker` as the Runtime (it will automatically use the `Dockerfile` in the root).
5. Add any required environment variables in the **Environment** tab.
6. Click **Deploy Web Service**.
