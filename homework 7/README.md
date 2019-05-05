# Word Ladder Microservices

## Usage
1. Run `docker pull lingmhuany/wordladder-eureka-service`, `docker pull lingmhuany/wordladder-login` and `docker pull lingmhuany/wordladder-find-ladder`.
2. Enter the diretory where `docker-compose.yml` exists and run `docker-compose up -d`.
3. Open http://192.168.99.100:8761/, wait and refresh it until you see the Eureka service registry. If LOGIN and FIND-LADDER are not both shown, wait and refresh it.
4. Open http://192.168.99.100:8080/login in your browser or Postman, log in with username 'hhhh' and password 'hhhh'. (If you are using postman, make sure you login with `form-data`.) It will show `hello` if you login successfully.
5. Try http://192.168.99.100:8081/wordladder?word1=data&word2=cake. (If you are not signed in, it will return `Need login`.)