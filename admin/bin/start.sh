#!/bin/bash
nohup /usr/bin/java -server -d64 -Xms4g -Xmx4g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar admin-0.0.1-SNAPSHOT.jar -Dspring.config.location=application.properties -DadminService > console.log &