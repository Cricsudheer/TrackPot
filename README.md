# TrackPot

[![Java CI with Maven](https://github.com/Cricsudheer/TrackPot/actions/workflows/maven.yml/badge.svg)](https://github.com/Cricsudheer/TrackPot/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

TrackPot is a Spring Boot application for tracking snooker matches and scores.

## Features

- Player management
- Match tracking
- Frame-by-frame scoring
- Real-time updates via WebSockets
- RESTful API for integration

## Technology Stack

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- WebSockets
- Maven

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

### Database Setup

1. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE trackpot;
   ```

2. Configure database connection in `application.properties` or use environment variables:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/trackpot
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Building the Application

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8080

## API Documentation

API documentation is available via Swagger UI at http://localhost:8080/swagger-ui.html when the application is running.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## GitHub Setup

If you're having issues with GitHub authentication, please refer to the [GitHub Setup Guide](docs/github-setup.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.