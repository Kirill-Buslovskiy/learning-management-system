FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /learning-management-system

COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src

RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /learning-management-system

RUN useradd --system --no-create-home --uid 1001 appuser
COPY --from=build /learning-management-system/target/*.jar learning-management-system.jar
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "learning-management-system.jar"]