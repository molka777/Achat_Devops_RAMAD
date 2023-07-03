FROM openjdk:8-jdk

RUN curl -u admin:nexus -o /achat-1.0.jar "http://192.168.43.54:8081/#browse/browse:maven-releases:tn%2Fesprit%2Frh%2Fachat%2F1.0%2Fachat-1.0.jar"

EXPOSE 8089

ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
