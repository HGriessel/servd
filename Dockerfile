FROM openjdk:11-jdk-slim AS build

WORKDIR /app

COPY . .

RUN chmod +x grailsw

RUN ./grailsw war

FROM openjdk:11-jre-slim

ENV GRAILS_ENV=production
ENV APP_HOME=/app

WORKDIR $APP_HOME

COPY --from=build /app/build/libs/*.war $APP_HOME/app.war

EXPOSE 8080

CMD ["java", "-Dgrails.env=production", "-jar", "app.war"]

