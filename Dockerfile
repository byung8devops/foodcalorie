FROM openjdk:8-jdk-alpine

ARG TIMEZONE="Asia/Seoul"

RUN apk add tzdata
RUN cp /usr/share/zoneinfo/${TIMEZONE} /etc/localtime
RUN echo "Asia/Seoul" > /etc/timezone
ENV LANG=ko_KR.UTF-8

RUN addgroup -g 1000 bd
RUN adduser -u 1000 -G bd -D bd

RUN mkdir -p /data/foodcalorie/run
RUN mkdir -p /data/foodcalorie/logs
RUN mkdir -p /data/foodcalorie/lib
RUN mkdir -p /data/foodcalorie/conf
COPY target/foodcalorie-0.0.1-SNAPSHOT.jar /data/foodcalorie/lib/app.jar
COPY src/main/resources/application.yml /data/foodcalorie/conf/application.yml
RUN chown -R bd:bd /data
USER bd

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.config.location=/data/foodcalorie/conf/application.yml","-Duser.timezone=Asia/Seoul","/data/foodcalorie/lib/app.jar"]
