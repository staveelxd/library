FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/library-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]