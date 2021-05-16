#FROM adoptopenjdk/openjdk15:alpine-jre
#ADD target/springboot-mongodb-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","webpro.jar"]


FROM openjdk:15-jdk-alpine
MAINTAINER "Docker App <docker@app.com>"
WORKDIR /app

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080