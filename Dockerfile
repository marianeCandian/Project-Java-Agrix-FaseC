FROM maven:3-openjdk-17 AS build-image
WORKDIR /to-build-app
COPY src ./src
COPY pom.xml .
RUN mvn dependency:go-offline
RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /to-build-app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]