FROM openjdk:11
ARG JAR_FILE=target/*.jar
RUN curl -u admin:admin -o achat-1.0.jar "http://192.168.163.67:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" -L
ENTRYPOINT ["java","-jar","target/achat-1.0.jar"]
EXPOSE 8089
