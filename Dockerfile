FROM openjdk:2.8-jdk

RUN curl -u admin:nexus -o /app.jar "http://192.168.43.54:8081/#browse/browse:maven-releases:tn%2Fesprit%2Frh%2Fachat%2F1.0%2Fachat-1.0.jar"

EXPOSE 8089

CMD ["java", "-jar", "/app.jar"]
