FROM openjdk:8-jre-alpine
COPY ./build /app/build
ADD ./build/libs/eureka-service-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]

