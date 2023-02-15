ROM openjdk:11
expose 8002
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y maven

COPY ./ ./
RUN mvn clean test package
CMD ["java", '-jar', 'target/ms-user.jar'