FROM adoptopenjdk/openjdk11-openj9:alpine-jre
EXPOSE 8080
RUN apk update
ENV APP_HOME=/app
WORKDIR $APP_HOME
ADD --chown=app:app build/libs/*.jar app.jar
USER app
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=90","-XX:MinRAMPercentage=50", "-XshowSettings:vm" ,"-jar","/app/app.jar"]