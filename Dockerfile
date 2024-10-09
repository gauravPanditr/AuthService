FROM openjdk:21


WORKDIR /app


COPY app.jar /app/app.jar


EXPOSE 9898


ENTRYPOINT ["java", "-jar", "/app/app.jar"]
