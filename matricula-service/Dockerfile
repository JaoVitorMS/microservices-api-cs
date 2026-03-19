FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts
RUN chmod +x gradlew
RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon help
COPY src src
RUN --mount=type=cache,target=/root/.gradle ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
