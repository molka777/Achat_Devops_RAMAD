FROM openjdk:11-jdk

RUN curl -u admin:nexus -o /app.jar "http://172.18.0.2:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"

CMD ["java", "-jar", "/app.jar"]
