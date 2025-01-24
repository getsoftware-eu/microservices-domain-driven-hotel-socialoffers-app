FROM openjdk:17-jdk-slim
COPY target/kafka-stream-topdeal-app.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]
