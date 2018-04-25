# Sample for Spring Cloud Sleuth - Users Microservice
Simple project to show how spring-clouh-sleuth works.

## Running
```# mvn spring-boot:run```

## Available APIs
### Create user
``` 
curl -X POST \
  http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -d '{
    "email" : "john@test.com", "name" : "John"
}' 
```

### Create random user
``` 
curl -X POST http://localhost:8080/users/someone 
```

### Listing users
```curl http://localhost:8080/users```