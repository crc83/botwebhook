FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
WORKDIR /
ADD build/libs/botwebhooks-0.0.1-SNAPSHOT.jar botwebhooks-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar botwebhooks-0.0.1-SNAPSHOT.jar