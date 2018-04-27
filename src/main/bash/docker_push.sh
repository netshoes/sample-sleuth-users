#!/usr/bin/env bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push netshoes/sample-sleuth-users:latest
docker push netshoes/sample-sleuth-users:1.0-SNAPSHOT