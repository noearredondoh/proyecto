FROM amazoncorretto:17-alpine-jdk
LABEL authors="Invexort"
COPY target/invexort-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]