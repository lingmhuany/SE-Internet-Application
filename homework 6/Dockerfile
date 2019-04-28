FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
COPY dictionary.txt dictionary.txt
ENTRYPOINT ["java","-jar","/app.jar"]