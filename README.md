# Quiz Microservices Project

A Spring Boot microservices system demonstrating:

- Eureka Service Discovery
- Spring Cloud API Gateway
- OpenFeign inter-service communication
- Load-balanced service instances

## Architecture

- **Eureka Server** – service registry
- **API Gateway** – single entry point
- **Question Service** – manages questions
- **Quiz Service** – creates and evaluates quizzes

## Tech Stack

- Java 21
- Spring Boot 4.x
- Spring Cloud 2025.x
- Netflix Eureka
- Spring Cloud Gateway
- OpenFeign
- Maven

## Services & Ports

| Service           | Port |
|------------------|------|
| Eureka Server     | 8761 |
| API Gateway      | 8083 |
| Question Services | 8080 - 8081 |
| Quiz Service     | 8082 |

## How to Run

1. Start **Eureka Server**
2. Start **Question Services**
3. Start **Quiz Service**
4. Start **API Gateway**

Access:
- Eureka: http://localhost:8761
- Gateway: http://localhost:8083

## Example Requests

```http
GET http://localhost:8083/quiz-service/quiz/get/1
