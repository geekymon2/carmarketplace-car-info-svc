# Car Marketplace

Microservices App for Car Marketplace

## Status

[![build](https://github.com/geekymon2/carmarketplace-car-info-svc/actions/workflows/build.yml/badge.svg)](https://github.com/geekymon2/carmarketplace-car-info-svc/actions/workflows/build.yml) &nbsp;&nbsp; [![codecov](https://codecov.io/gh/geekymon2/carmarketplace-car-info-svc/branch/main/graph/badge.svg?token=LH7ATDIHTB)](https://codecov.io/gh/geekymon2/carmarketplace-car-info-svc) &nbsp;&nbsp; ![Docker Image Version (latest by date)](https://img.shields.io/docker/v/geekymon2/cm-car-info-svc) &nbsp;&nbsp; ![Docker Image Size (latest by date)](https://img.shields.io/docker/image-size/geekymon2/cm-car-info-svc)

## About this Service

**Car Info Service**

This micro service provides information for car make brands and models. Get information on all the makes, models, and types of cars, also filter them by make and type. 

## Local Environment Setup
* To run locally set the SPRING profile to "local". 
  * You can do this by setting environment variable SPRING_PROFILES_ACTIVE=local
  * you can also set profile within the Intellij IDE
* Set the following environment variables.
  * CARINFO_DB_NAME = carinfodbdev
  * CARINFO_DB_PASSWORD = [PASSWORD]
* Direct API Swagger URL: http://localhost:8082/api/car-info-service/swagger-ui/index.html
* API Gateway: http://localhost:8008/api/swagger-ui/index.html

## CI environment Setup
* CI is configured to use profile "ci"
* TODO (Not working): Swagger URL: http://localhost:8001/api/v1/namespaces/carmarketplace-dev/services/http:car-info-service:8082/proxy/

## Documentation

For more details refer to the swagger documentation.