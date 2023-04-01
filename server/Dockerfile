FROM ubuntu/sapmachine-20-jdk:latest
ADD target/onlinewhiteboard-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]