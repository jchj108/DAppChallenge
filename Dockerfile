FROM openjdk:17-oracle AS builder

ARG JAR_FILE=/build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]