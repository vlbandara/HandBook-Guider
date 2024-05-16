
# HandBook-Guider

HandBook-Guider is a Spring Boot application that loads a PDF document (Handbook of Computer engineering department, University of Jaffna) into a vector store and provides a command-line interface for asking questions related to the content of the PDF.

## Prerequisites

- Java 11 or later
- Maven
- Docker (for running PostgreSQL)

## Getting Started

1. Clone the repository:

   ```
   git clone https://github.com/your-username/HandBook-Guider.git
   ```

2. Navigate to the project directory:

   ```
   cd HandBook-Guider
   ```

3. Build the project:

   ```
   mvn clean install
   ```

4. Start the Docker container for PostgreSQL:

   ```
   docker-compose up -d
   ```

5. Run the application:

   ```
   mvn spring-boot:run
   ```

   The application will start, and you should see log messages indicating that the PDF document has been loaded into the vector store.

## Usage

Once the application is running, you can access the Spring Shell command-line interface. To ask a question, use the `q` command followed by your question:

```
q Tell me about this course?
```

The application will use the loaded PDF content and the OpenAI language model to provide an answer to your question.

## Configuration

The application uses the following configuration properties:

- `spring.datasource.url`: The URL for the PostgreSQL database (default: `jdbc:postgresql://localhost:5432/postgres`).
- `spring.datasource.username`: The username for the PostgreSQL database (default: `postgres`).
- `spring.datasource.password`: The password for the PostgreSQL database (default: `postgres`).
- `openai.api.key`: The API key for OpenAI (required, set this in your environment variables or provide it when running the application).

You can override these properties by creating a `application.properties` file in the `src/main/resources` directory or by passing them as command-line arguments when running the application.

