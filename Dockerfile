FROM openjdk:8-jdk-alpine

RUN addgroup -g 1000 bd
RUN adduser -u 1000 -G bd -D bd

RUN mkdir -p /foodcalorie/run
RUN mkdir -p /foodcalorie/logs
RUN mkdir -p /foodcalorie/lib
RUN mkdir -p /foodcalorie/conf
COPY target/foodcalorie-0.0.1-SNAPSHOT.jar /foodcalorie/lib/app.jar
COPY src/main/resources/application.yml /foodcalorie/conf/application.yml
RUN chown -R bd:bd /foodcalorie
USER bd

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.config.location=/foodcalorie/conf/application.yml","/foodcalorie/lib/app.jar"]
