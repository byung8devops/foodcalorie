FROM openjdk:8-jdk-alpine

RUN addgroup -g 1000 bd
RUN adduser -u 1000 -G bd -D bd

RUN mkdir -p /foodcalorie/run
RUN mkdir -p /foodcalorie/logs
RUN mkdir -p /foodcalorie/lib
COPY target/foodcalorie-0.0.1-SNAPSHOT.jar /foodcalorie/lib/app.jar
RUN chown -R bd:bd /foodcalorie
USER bd

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=prod","/foodcalorie/lib/app.jar"]

