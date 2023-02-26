FROM openjdk:11
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y maven

COPY ./ ./
RUN mvn clean test package
CMD ["java","-jar","target/ms-eureka-server.jar"]
