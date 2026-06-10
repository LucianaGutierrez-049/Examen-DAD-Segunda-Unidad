FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/ms-lib-multas.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/ms-lib-multas.jar"]
