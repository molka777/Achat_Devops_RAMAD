FROM openjdk:11-jdk

RUN curl -u admin:nexus -o /app.jar "http://localhost:8081/#browse/browse:maven-releases:tn%2Fesprit%2Frh%2Fachat%2F1.0%2Fachat-1.0.jar"

CMD ["java", "-jar", "/app.jar"]
