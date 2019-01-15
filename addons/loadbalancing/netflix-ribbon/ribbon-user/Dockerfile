FROM openjdk:8-jre-alpine
RUN apk add --update \
    curl jq\
    && rm -rf /var/cache/apk/*

COPY ./build /app/build
ADD ./build/libs/ribbon-user-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]