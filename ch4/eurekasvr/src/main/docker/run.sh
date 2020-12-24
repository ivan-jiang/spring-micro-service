#!/bin/sh


echo "*****************************************************"
echo "String License Server with Configuration Service: $CONFIGSERVER_URI"
java -Dspring.cloud.config.uri=$CONFIGSERVER_URI \
	-Dspring.profiles.active=$PROFILE \
	-jar /usr/local/eurekaservice/@project.build.finalName@.jar
