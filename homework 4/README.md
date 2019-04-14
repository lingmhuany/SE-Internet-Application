# Word Ladder with Spring Boot (Actuator and Security)

## Run
1. Run `mvn spring-boot:run` in the command line.
2. Open http://localhost:8080/ in your browser or Postman.
3. Log in with username 'hhhh' and password 'hhhh'.
4. Try http://localhost:8080/wordladder?word1=data&word2=cake.
5. Try http://localhost:8080/actuator, http://localhost:8080/actuator/health and http://localhost:8080/actuator/info...
6. If you haven't logged in, they will return 401 error.

## Test
Run `mvn test` in the command line.
