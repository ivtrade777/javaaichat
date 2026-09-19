# JavaWebAI

JavaWebAI is a lightweight Spring Boot application that exposes a simple REST API for sending chat prompts to OpenAI's Chat Completions endpoint and returning the generated response.

It is designed as a backend service for web or mobile frontends that need AI-powered chat capabilities without embedding OpenAI client logic directly in the client application.

## Overview

This project uses:

- Java 25
- Spring Boot 4.1.1
- Spring WebFlux
- Reactor
- OpenAI Chat Completions API

The application receives a message from a client, forwards it to OpenAI, and returns the AI response in a JSON body.

## Features

- Reactive REST API built with Spring WebFlux
- Chat endpoint for sending user prompts
- OpenAI integration with bearer token authentication
- CORS configuration for local frontend development
- Simple request/response DTOs for clean API contracts

## Project structure

```text
src/
├── main/
│   ├── java/com/example/javawebai/
│   │   ├── config/
│   │   │   └── WebClientConfig.java
│   │   ├── controller/
│   │   │   └── ChatController.java
│   │   ├── dto/
│   │   │   ├── ChatRequest.java
│   │   │   └── ChatResponse.java
│   │   ├── service/
│   │   │   └── ChatService.java
│   │   └── JavaWebAiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/javawebai/JavaWebAiApplicationTests.java
```

## API endpoint

### POST /api/chat

Request body:

```json
{
  "message": "Write a short welcome message for a developer portal."
}
```

Example response:

```json
{
  "message": "Welcome! We’re glad to have you here..."
}
```

The controller is configured to accept requests from `http://localhost:4200` via CORS.

## Configuration

Set your OpenAI API key as an environment variable before running the app:

### Linux / macOS

```bash
export OPENAI_API_KEY="your_openai_api_key_here"
```

### Windows PowerShell

```powershell
$env:OPENAI_API_KEY="your_openai_api_key_here"
```

The application reads this value from `src/main/resources/application.properties`:

```properties
spring.application.name=JavaWebAI
openai.api.key=${OPENAI_API_KEY}
```

## Running the application

From the project root:

```bash
./mvnw spring-boot:run
```

The app runs by default on:

```text
http://localhost:8080
```

## Example request

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Explain what Spring Boot is in one paragraph."}'
```

## Notes

- The backend currently sends requests to the OpenAI Chat Completions API using the `gpt-5-mini` model.
- The service is reactive and non-blocking, which suits modern web APIs and scalable integrations.
- This application is a good starting point for building AI-powered web apps, chat UIs, or internal assistants.

## License

This project is a sample application for learning and experimentation.
