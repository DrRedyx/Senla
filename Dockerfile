FROM openjdk:17-jdk-alpine
MAINTAINER Nigamatullin Ilyas
COPY target/Senla-0.0.1-SNAPSHOT.jar senla.jar
ENTRYPOINT ["java", "-jar", "/senla.jar"]