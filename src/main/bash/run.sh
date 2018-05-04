#!/bin/bash

export NOTIFICATION_ADDRESS=$1	# Address of Notification service. e.g. http://localhost:8081
export ZIPKIN_ADDRESS=$2        # Address of Zipkin. e.g. http://localhost:9411

exec $(type -p java) \
  -jar /opt/sample-sleuth-users.jar  \
  --application.notification.address=${NOTIFICATION_ADDRESS} \
  --spring.zipkin.baseUrl=${ZIPKIN_ADDRESS}