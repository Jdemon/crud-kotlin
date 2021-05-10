FROM adoptopenjdk/openjdk11-openj9:alpine-jre
EXPOSE 8080
RUN apk update
ENV APP_HOME=/app
WORKDIR $APP_HOME
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=90","-XX:MinRAMPercentage=50", "-XshowSettings:vm" ,"-jar","/app/app.jar"]