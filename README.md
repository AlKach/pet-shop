# pet-shop [![CI status](https://travis-ci.org/pet-shop/pet-shop.svg?branch=master)](https://travis-ci.org/pet-shop/pet-shop) [![BCH compliance](https://bettercodehub.com/edge/badge/pet-shop/pet-shop?branch=master)](https://bettercodehub.com/) [![Sonar status](https://sonarcloud.io/api/project_badges/measure?project=pet-shop_pet-shop&metric=alert_status)](https://sonarcloud.io/dashboard?id=pet-shop_pet-shop)

Simple internet shop application. Created for practicing in developing web application from scratch.

Runtime environment:
 - JVM 1.8
 - Tomcat 7
 - PostgreSQL 9.1

Frameworks and libraries:
 - [Spring](https://spring.io/) - IOC container, MVC, data access, transactions handling
 - [RSQL](https://github.com/jirutka/rsql-parser) - query parser
 - [Flywaydb](https://flywaydb.org/) - DB migration
 - [Swagger](http://swagger.io/) + [Springfox](http://springfox.github.io/springfox/) - API documentation
 
## Environment setup

pet-shop uses PostgreSQL DB to store its data. It is recommended to run DB on separate machine.

### Docker

The easiest way to get PostgreSQL DB up and running for development and testing is to use Docker image:

    docker run --name pg-test \
               -p 5432:5432 \
               -e POSTGRES_USER=shop_test \
               -e POSTGRES_PASSWORD=password \
               -e POSTGRES_DB=shop_test \
               -d postgres:alpine

This will start Docker container with PostgreSQL instance with DB `shop_test`, user `shop_test` and password `password`.

This approach is used in the build script. By default task `test` will start PostgreSQL for tests in Docker container. If you want to run tests on your own DB, comment out property `testProfile` in `gradle.properties`.

## Running

Project contains `docker-compose` configuration to run all application's containers. Before running you will need to build application images:

    ./gradlew buildImages
    docker-compose up