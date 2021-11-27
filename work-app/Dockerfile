# Dependencies
FROM maven:latest AS maven
WORKDIR /app
COPY pom.xml .
#RUN mvn -e -B dependency:resolve

# Plugins
#RUN mvn -e -B dependency:resolve-plugins

# Classes
COPY src/main/java ./src/main/java
COPY src/main/resources ./src/main/resources
RUN mvn -e -B clean package -DskipTests -Dmaven.javadoc.skip=true

FROM openjdk:8
COPY --from=maven /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]