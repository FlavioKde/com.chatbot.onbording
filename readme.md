# Onboarding chatbot

A simple conversational assistant to answer frequently asked questions about the onboarding process for new employees. Developed with Java and Spring Boot, with in-memory persistence using H2.

## Prerequisites

- java >= 17
- Maven o Gradle
- Port 8080 available
- Persistence H2
- Basic test JUnit
- Swagger Docs


## Tech Stack

- **Backend**: Java 21, Spring Boot 3
- **Database**: H2
- **Build Tool**: Gradle
- **API**: RESTful endpoints with Swagger/OpenAPI
- **Testing**: JUnit
- **Tools**: Git, GitHub

## Architecture Overview

This project follows a layered architecture inspired by Domain-Driven Design (DDD), with clear separation of concerns:

### Domain Layer
- Encapsulates core business logic (`Knowledge.java`, `Chatbot.java`)
- Domain models and value objects
- Repository interfaces for persistence abstraction (`KnowledgeRepository.java`)

### Application Layer
- Service classes orchestrating business use cases (`KnowledgeService`, etc.)
- Data Transfer Objects (DTOs) for structured input/output
- Chatbot configuration (`ChatbotConfig`) for modular setup and behavior control
- Input validation and response formatting

### Infrastructure Layer
- Persistence implementation (e.g., H2 adapters)
- REST controllers (`KnowledgeController`)
- Configuration classes and external integrations

### Shared Layer
- `EntityMapper` for domain-to-entity conversion
- Custom exception classes and global error handling


## Installation and Execution

Follow these steps to set up and run the project in your local environment.

###  Quick Start

```bash

git clone https://github.com/FlavioKde/com.chatbot.onbording.git
cd onboarding

```

### Run the application

You can launch the Spring Boot application in several ways:

Option A: Using Gradle from command line (Recommended)

```bash

./gradlew bootRun

```

On Windows:

```bash

gradlew.bat bootRun

```

Option B: Using your IDE

```text

- Import the project as a Gradle project in your IDE

- Locate the main application class (typically Application.java or OnboardingChatbotApplication.java)

- Right-click and select Run

```


Option C: Building an executable JAR

```bash

./gradlew build
java -jar build/libs/onboarding-chatbot-0.0.1-SNAPSHOT.jar

```

### Acces de application

Once the application is running (you'll see Spring Boot startup messages in the terminal), you can access the following endpoints:

|    Component     |                   URL                         |           Purpose             |
|:----------------:|:---------------------------------------------:|:------------------------------|
| **API REST**     | `http://localhost:8080/api`                   | Main API endpoints |
| **Swagger UI**   | `http://localhost:8080/swagger-ui/index.html` | Interactive API documentation |
| **H2 Console**   | `http://localhost:8080/h2-console`            | In-memory database management |
| **Health Check** | `http://localhost:8080/actuator/health`       | Application status |


### H2 Database Configuration

When accessing the H2 Console, use these credentials:

```text

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (leave empty)

```

### Testing

Run the test suite to verify everything is working correctly:

```bash

./gradlew test
Configuration
Main configuration file: src/main/resources/application.properties

To change the server port, add server.port=9090 to the configuration

The application uses in-memory H2 database by default (data persists until application restart)

```

### Examples Of Queries

Tú: Hola, buen dia
Bot: Muy bien y tu?
Tú: Donde firmo mi contrato?
Bot: En la oficina de RRHH, debes solicitar cita previa
Tú: exit
Bot: 👋 Hasta luego!

## ✅ Proves de l'aplicació

- Integration test for `GET /api/V0.1/getAllknowledge`
- Creation test with `POST /api/V0.01/ask`
- Console test with simulated input
- Behavior test when no response is found

## 📚 Documentació REST

The documentation is available through Swagger UI:

http://localhost:8080/swagger-ui/index.html


Includes:
- Avalible Endpoints
- Data models (`Knowledge`)
- Expected response code


## 👤 Autoria

Flavio — freelance WebDeveloper  
Les Fonts de Terrassa, Catalunya  





