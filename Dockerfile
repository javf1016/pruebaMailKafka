FROM openjdk:17-alpine

WORKDIR /app

COPY target/mail-0.0.1-SNAPSHOT.jar /app/mail-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application-docker.properties /app/application-docker.properties
COPY src/main/resources/consumer.properties /app/consumer.properties

EXPOSE 8080

CMD ["java", "-jar", "mail-0.0.1-SNAPSHOT.jar"]

