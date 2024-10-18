FROM openjdk:17-jdk

WORKDIR /app

COPY target/api-0.0.1-SNAPSHOT.jar /app/api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/api.jar"]

