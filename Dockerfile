FROM openjdk:8
EXPOSE 8089

RUN curl -u admin:nexus -o /achat.jar "http://192.168.43.54:8081/#browse/browse:maven-releases:tn%2Fesprit%2Frh%2Fachat%2F1.0%2Fachat-1.0.jar"

ENTRYPOINT ["java", "-jar", "/achat-1.0.jar"]

FROM openjdk:11-jdk
