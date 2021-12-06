FROM java:11
WORKDIR /
ADD botwebhook-0.0.1-SNAPSHOT.jar botwebhook-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar botwebhook-0.0.1-SNAPSHOT.jar