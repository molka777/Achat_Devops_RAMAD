FROM openjdk:11
ARG JAR_FILE=target/*.jar
RUN apk --no-cache add curl
RUN curl -u admin:nexus -o achat-1.0.jar "http://localhost/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" -L
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
EXPOSE 8089
