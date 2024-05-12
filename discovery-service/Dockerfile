FROM amazoncorretto:21-alpine
RUN apk --no-cache add curl
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8001