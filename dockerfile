FROM openjdk:17-jdk-alpine

COPY petminder1.jar petminder1.jar

ENTRYPOINT [ "java", "-jar", "petminder1.jar" ]