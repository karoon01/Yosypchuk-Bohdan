FROM openjdk:8-jre-alpine

COPY["target/homework3.jar", "homework3.jar"]
EXPOSE 8080

ENTRYPOINT["java", "-jar", "homework3.jar"]