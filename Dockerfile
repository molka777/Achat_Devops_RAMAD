FROM openjdk:8
EXPOSE 8089

ADD /target/achat.jar achat.jar
ENTRYPOINT ["java", "-jar", "/achat.jar"]
