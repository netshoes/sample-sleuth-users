# Sample for Spring Cloud Sleuth - Users Microservice
Simple project to show how spring-cloud-sleuth works.

# Running
## Application
```# mvn spring-boot:run```

## Zipkin
```
docker pull openzipkin/zipkin:2.7.3
docker run -d --name openzipkin-2.7.3 -p 9411:9411 openzipkin/zipkin:2.7.3
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