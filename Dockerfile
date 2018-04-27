FROM openjdk:8-jdk

MAINTAINER Grupo Netshoes

ADD src/main/bash/run.sh /opt/run.sh
RUN chmod +x /opt/run.sh
ADD target/sample-sleuth-users.jar /opt/sample-sleuth-users.jar
EXPOSE 8080
ENTRYPOINT ["/opt/run.sh"]