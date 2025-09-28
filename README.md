# MyExpense API

Kotlin- and Spring Boot–based REST API for managing personal expenses, categories, and user accounts. The service provides CRUD-style endpoints to track spending, organize transactions by category, and manage user roles. Data is persisted in PostgreSQL, with schema versioning and migrations handled through Flyway.

## Tech Stack
- Kotlin 1.9
- Spring Boot 3.5 (Web, Data JPA, Validation)
- PostgreSQL + Flyway
- Gradle Kotlin DSL
- JUnit 5

## Project Structure
```
src/
  main/
    kotlin/com/skuknuraknu/myexpense
      controller/        // REST endpoints (UserController)
      dto/               // Request & response DTOs
      entity/            // JPA entities (User, Role, UserRole)
      exception/         // Global exception handling
      repository/        // Spring Data JPA repositories
      service/           // Business services
    resources/
      application.yaml   // Spring configuration
      db/migrations/     // Flyway SQL migrations
```

## Prerequisites
- JDK 21 (configured via Gradle toolchain)
- PostgreSQL 13+ running locally (default URL `jdbc:postgresql://localhost:5432/expense`)
- Gradle wrapper (included) and Git

## Configuration
Default configuration is stored in `src/main/resources/application.yaml`. Update the following properties (or override via environment variables / command-line arguments) to match your local database credentials:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/expense
    username: ourusername
    password: ourpassword
  flyway:
    enabled: true
    locations: classpath:db/migrations
    url: jdbc:postgresql://localhost:5432/expense
    user: ourusername
    password: ourpassword
```

## Database Migrations
Flyway is wired to run automatically on application start.

## Running the Application
1. Ensure PostgreSQL is running and accessible with the credentials above.
2. From the project root, start the service:
   ```bash
   ./gradlew bootRun
   ```
3. The API listens on `http://localhost:8080` by default.

To build without running:
```bash
./gradlew build
```

## Testing
Run the Kotlin and Spring tests with:
```bash
./gradlew test
```

## Future Enhancements
- Password hashing and authentication/authorization (e.g., Spring Security).
- Role management endpoints and seeding scripts.
- Integration tests covering the REST API.
- Docker Compose for easy Postgres provisioning.
---
Maintained by `com.skuknuraknu` · Contributions welcome.
