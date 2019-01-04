FROM openjdk:8-alpine
ARG APP_NAME=pet-shop-web
ARG APP_VERSION=1.0
ARG APP_PORT=9090
ENV APP_NAME $APP_NAME
ADD ["build/libs/$APP_NAME-$APP_VERSION.jar", "/$APP_NAME.jar"]
EXPOSE ${APP_PORT}
CMD java -jar -Dspring.profiles.active=docker /$APP_NAME.jar