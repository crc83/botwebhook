# botwebhook
Small service to serve as a webhook for Viber or Telegram bots

# How to generate/regenerate wiremocks

1. Download wiremock server 

Page where to download: http://wiremock.org/docs/download-and-installation/

Direct link: https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-jre8-standalone/2.32.0/wiremock-jre8-standalone-2.32.0.jar

2. Launch standalone wiremock server 

At: http://localhost:8080/__admin/recorder/

Set Target URL to: https://api.telegram.org

And press "Record"

3. Comment out @AutoConfigureWireMock
4. In application.yml set "wiremock.server.port" to 8080
