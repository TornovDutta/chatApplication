Kafka Chat Application (Spring Boot Backend)

This is a **backend-only chat application** built using **Spring Boot** and **Apache Kafka (KRaft mode)**. The application allows sending and receiving chat messages via REST API endpoints.

Kafka is run using **Docker**.

---

## **Features**

- Send messages to a Kafka topic.
- Consume messages from the Kafka topic.
- Fully backend-driven (REST APIs only).
- Works with Kafka in **KRaft mode** (no Zookeeper needed).
- Uses JSON serialization for messages.

---

## **Tech Stack**

- **Java 17+**
- **Spring Boot**
- **Apache Kafka (KRaft mode)**
- **Docker** (for Kafka broker)
- **Maven** (for dependency management)

---

## **Project Structure**

```
src/
└── main/
    └── java/
        └── org/
            └── example/
                └── chatapplication/
                    ├── controller/
                    │   └── ChatController.java
                    ├── producer/
                    │   └── ChatProducer.java
                    ├── consumer/
                    │   └── ChatConsumer.java
                    └── model/
                        └── Messages.java

```

---

## **Setup Instructions**

### 1. Clone the repository
```bash
git clone https://github.com/TornovDutta/chatApplication.git
cd ChatApplication
```

### 2. Start Kafka using Docker
```bash
docker-compose up -d
```

### 3. Start Spring Boot backend
```bash
mvn spring-boot:run
```

### 4. Test the REST API
- Send messages
- View consumed messages

---

## **Docker Compose for Kafka (KRaft Mode)**

```yaml
version: '3.8'

services:
  kafka:
    image: apache/kafka:4.0.1
    container_name: kafka
    environment:
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_NODE_ID: 1
      KAFKA_LISTENERS: PLAINTEXT://:9092,CONTROLLER://:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_CONTROLLER_QUORUM_VOTERS: 1@localhost:9093
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LOG_DIRS: /var/lib/kafka/data
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'true'
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      CLUSTER_ID: MkU3OEVBNTcwNTJENDM2Qk
    ports:
      - "9092:9092"
    volumes:
      - kafka_data:/var/lib/kafka/data

volumes:
  kafka_data:

```

---

## **Application Properties (`application.properties`)**

```properties
spring.application.name=chat-application
server.port=8080

spring.kafka.bootstrap-servers=localhost:9092

# Producer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.acks=all

# Consumer
spring.kafka.consumer.group-id=chat-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

# Logging
logging.level.org.springframework.kafka=INFO
logging.level.org.apache.kafka=INFO

```
---

## **REST API Endpoints**

| Method | Endpoint        | Parameters                         | Description                                 |
|--------|----------------|-----------------------------------|---------------------------------------------|
| GET    | `/api/message` | None                              | Returns all messages consumed from Kafka.   |
| POST   | `/api/message` | `Messages` JSON object in request body | Sends a message to Kafka.                  |

### Example Request (POST)

```http
POST http://localhost:8080/api/message
Content-Type: application/json

{
    "sender": "Tornov",
    "content": "Hello Kafka",
    "time": "2025-10-12T22:00:00"
}
```

### Example Response (GET)

```json
{

  "sender": "Tornov",
  "content": "Hello Kafka",
  "time": "2025-10-12T22:00:00"

}
```

---

## **Notes**

- Kafka runs on port `9092`.
- Spring Boot backend runs on port `8080`.
- Messages are sent as plain strings to the Kafka topic `chat_topic`.
- No frontend included , fully backend and REST API driven.

