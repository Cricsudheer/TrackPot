# Database Connection Guide

This document provides information on how to check and troubleshoot database connections in the Trackpot application.

## Database Configuration

The Trackpot application uses PostgreSQL as its database. The connection settings are configured in the following files:

- `src/main/resources/application.properties`: Contains general configuration settings
- `src/main/resources/application-dev.properties`: Contains development-specific settings

For production environments, it's recommended to set the database connection properties using environment variables rather than hardcoding them in the properties files.

## Checking Database Connection

There are two ways to check the database connection in the Trackpot application:

### 1. Using the Custom Health Check Endpoint

The application provides a custom endpoint specifically for checking the database connection:

```
GET /api/health/db
```

This endpoint attempts to execute a simple query (`SELECT 1`) against the database and returns:

- **200 OK** with status "UP" if the connection is successful
- **503 Service Unavailable** with status "DOWN" if the connection fails

Example response for a successful connection:

```json
{
  "status": "UP",
  "message": "Database connection is working",
  "details": {
    "test_query": "SELECT 1",
    "result": 1
  }
}
```

Example response for a failed connection:

```json
{
  "status": "DOWN",
  "message": "Database connection failed",
  "error": "Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections."
}
```

### 2. Using Spring Boot Actuator

The application also includes Spring Boot Actuator, which provides comprehensive health monitoring capabilities, including database health checks.

To check the database connection using Actuator:

```
GET /actuator/health
```

This endpoint returns detailed health information about the application, including the database connection status.

Example response for a healthy application:

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "PostgreSQL",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 500107862016,
        "free": 377666564096,
        "threshold": 10485760,
        "exists": true
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

If the database connection is down, the "db" component will show a status of "DOWN" with details about the error.

## Troubleshooting Database Connection Issues

If the database connection check fails, here are some steps to troubleshoot:

1. **Verify database server is running**:
   ```
   pg_isready -h localhost -p 5432
   ```

2. **Check connection properties**:
   - Verify the URL, username, and password in the application properties
   - For development: Check `application-dev.properties`
   - For production: Check environment variables or deployment configuration

3. **Test direct connection**:
   ```
   psql -h localhost -p 5432 -U username -d database_name
   ```

4. **Check network connectivity**:
   ```
   telnet localhost 5432
   ```

5. **Verify database user permissions**:
   - Ensure the user has the necessary permissions to connect to the database
   - Check if the user can execute queries on the required tables

6. **Check database logs**:
   - PostgreSQL logs may contain information about failed connection attempts
   - Look for authentication failures or connection rejections

## Setting Up Database Connection

To set up the database connection for different environments:

### Development Environment

1. Configure `application-dev.properties` with your local database settings:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_dev_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

2. Run the application with the dev profile:
   ```
   ./mvnw spring-boot:run -Dspring.profiles.active=dev
   ```

### Production Environment

For production, it's recommended to use environment variables:

```
export SPRING_DATASOURCE_URL=jdbc:postgresql://your-prod-host:5432/your_prod_db
export SPRING_DATASOURCE_USERNAME=your_prod_username
export SPRING_DATASOURCE_PASSWORD=your_prod_password
```

Then run the application:

```
./mvnw spring-boot:run
```

## Database Migration

The application uses Flyway for database migrations. Migrations are located in:

```
src/main/resources/db/migration
```

If you encounter issues with database schema, you can check the Flyway migration status:

```
GET /actuator/flyway
```

This endpoint provides information about applied migrations and pending migrations.