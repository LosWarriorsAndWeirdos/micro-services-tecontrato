FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
ADD build/libs/*.jar config-service.jar
ENV JAVA_OPTS=''
ENTRYPOINT ["java", "-jar", "/config-service.jar"]