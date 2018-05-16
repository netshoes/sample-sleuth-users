[![Build Status](https://travis-ci.org/netshoes/sample-sleuth-users.svg?branch=master)](https://travis-ci.org/netshoes/sample-sleuth-users)

# Sample for Spring Cloud Sleuth - Users Microservice
Simple project to show how spring-cloud-sleuth works. 
This application depends of [Notification microservice](https://github.com/netshoes/sample-sleuth-notification)

# Quickstart
The easiest way to run this example with all dependencies is from docker compose, with docker installed simple run at root of project:
``` 
docker-compose up
```
This command will start the following services:
* sample-sleuth-users in port 8080
* sample-sleuth-notification in port 8081
* zipkin in port 9411

Don't forget run ```docker-compose down``` after trial this sample.

# Running
## Dependencies
First you must install and run the dependencies.

### Zipkin
```
docker pull openzipkin/zipkin:2.7.3
docker run -d -p 9411:9411 --name openzipkin-2.7.3 openzipkin/zipkin:2.7.3
```
### Notification microservice
``` 
docker pull netshoes/sample-sleuth-notification
docker run -d --name sample-sleuth-notification -p 8081:8081 netshoes/sample-sleuth-notification
```

## Application
### From source code
```mvn spring-boot:run```

### From Docker
``` 
docker pull netshoes/sample-sleuth-users
docker run -d  -e NOTIFICATION_ADDRESS='http://localhost:8081' -e ZIPKIN_ADDRESS='http://localhost:9411' -p 8080:8080 --name sample-sleuth-users netshoes/sample-sleuth-users
```

# Available APIs
## Create user
``` 
curl -X POST \
  http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -d '{
    "email" : "john@test.com", "name" : "John"
}'
```

## Create random user
``` 
curl -X POST http://localhost:8080/users/someone
```

## Listing users
```curl http://localhost:8080/users```