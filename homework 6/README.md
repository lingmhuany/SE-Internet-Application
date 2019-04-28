# Dockerized Word Ladder with Spring Boot

## Usage
1. Run `docker pull lingmhuany/wordladder`
2. Run `docker run -p 8080:8080 lingmhuany/wordladder`
3. Try http://localhost:8080/wordladder?word1=data&word2=cake using `GET` method. (Someone can access it through `localhost`, but only `192.168.99.100` works for me.)