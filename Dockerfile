# Fase de construcción
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /app
COPY TP_Anual /app/TP_Anual
WORKDIR /app/TP_Anual
RUN mvn clean package assembly:single -DskipTests

# Fase de ejecución
FROM openjdk:18-jdk-slim
WORKDIR /app
COPY --from=build /app/TP_Anual/target/HeladeraTest-1.0-SNAPSHOT-jar-with-dependencies.jar DeccoColaboracion.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "DeccoColaboracion.jar"]
