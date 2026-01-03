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

## Example Requests (via API Gateway)

> Base URL: `http://localhost:8083`  
> All external traffic is routed through **Spring Cloud API Gateway**

### Quiz Service

#### Get quiz questions
Retrieve all questions for a given quiz.

```http
GET /quiz-service/quiz/get/{quizId}
```
#### Create a new quiz
Creates a quiz by dynamically fetching questions from the Question Service
Query parameters : category(question category), numQUestions(number of questions), title(quiz title)

```http
POST /quiz-service/quiz/create
```

Example: 

```http
POST http://localhost:8083/quiz-service/quiz/create?category=Java&numQuestions=3&title=Java
```

#### Submit quiz answers & calculate score
Evaluates the quiz using Question Service

```http
POST /quiz-service/quiz/submit/{quizId}
```

Example:

```http                                        
POST http://localhost:8083/quiz-service/quiz/submit/1
```

### Question Service

#### Get all questions
Retrieve all questions

```http
GET /question-service/question/allQuestions
```

#### Get questions by category

```http
GET /question-service/question/category/{category}
```

Example: 

```http
GET http://localhost:8083/question-service/question/category/Java
```

#### Add, Delete Questions              

```http
POST http://localhost:8083/question-service/question/add
```

```http
POST http://localhost:8083/question-service/question/delete/5
```


#### Generate randomm questions for a quiz
Returns question IDs used internally by the Quiz Service.

```http
GET /question-service/question/generate
```

Example:

```http
GET http://localhost:8083/question-service/question/generate?categoryName=Java&numQuestions=3
```

#### Service discovery

Eureka Dashboard:

```http
http://localhost:8761
```
