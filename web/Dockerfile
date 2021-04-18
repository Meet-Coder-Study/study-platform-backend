FROM openjdk:11.0.10-jre-slim
RUN addgroup --system spring && adduser spring --ingroup spring
USER spring:spring
ARG JAR_FILE=web/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

