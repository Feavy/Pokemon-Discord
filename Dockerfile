FROM openjdk:11
ADD build/libs/pokemon-discord-poc-1.0-SNAPSHOT-all.jar /app/pokemon.jar
WORKDIR /app
CMD ["java", "-jar", "pokemon.jar"]
