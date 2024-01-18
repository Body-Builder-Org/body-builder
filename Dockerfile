# syntax=docker/dockerfile:1

FROM openjdk:11 as prod
WORKDIR /app
RUN echo App is ready
COPY /spring-app/target/body-builder.jar .
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "body-builder.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "body-builder.jar"]

#FROM postgres:12 as base_app_db
#COPY config/pg_healthcheck /usr/local/bin/
#RUN chmod +x /usr/local/bin/pg_healthcheck
