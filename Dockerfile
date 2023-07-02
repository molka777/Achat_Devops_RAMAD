# Utilisez une image de base qui a Java préinstallé                                                                     
FROM openjdk:11                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
# Copiez le fichier JAR généré par Maven dans le conteneur Docker                                                       
COPY target/achat-1.0.jar /app.jar                                                                                                                                                                                                                                                                                                                                      
# Exécutez l'application lorsque le conteneur démarre                                                                   
CMD ["java", "-jar", "/app.jar"]
EXPOSE 8089
