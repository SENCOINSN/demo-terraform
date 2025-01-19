# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY ../pom.xml .
RUN mvn dependency:go-offline
COPY ../src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
#ARG PROFILE=dev
#ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/*.jar /app/

EXPOSE 8087

CMD java -jar demo-terraform-0.0.1-SNAPSHOT.jar