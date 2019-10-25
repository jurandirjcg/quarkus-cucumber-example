FROM frolvlad/alpine-java:jre8-slim

#ARG JAR_FILE

RUN ls -la

ADD paciente-service-thorntail.jar app.jar
#ADD target/${JAR_FILE} app.jar

EXPOSE 8080

RUN sh -c 'touch /app.jar'

#Corrige erro de execucao do JAX-RS no docker
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]