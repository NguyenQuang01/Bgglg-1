FROM maven:3.8.1-jdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
