FROM frolvlad/alpine-java:jre8-slim

VOLUME /tmp
#ADD ${project.build.finalName}.jar
ADD paciente-service.war app.war

RUN sh -c 'touch /app.war'
ENTRYPOINT [ "sh", "-c", "java -jar /app.war" ]