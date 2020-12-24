#!/bin/sh

echo "*****************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "*****************************************************"
while ! $(nc -z configserver "$CONFIGSERVER_PORT"); do sleep 3; done
echo ">>>>>>>>>>>> Configuration Server has started"

#echo "*****************************************************"
#echo "Waiting for the database server to start on port $DATABASE_PORT"
#while ! $(nc -z database "$DATABASE_PORT"); do sleep 3; done
#echo ">>>>>>>>>>>> Database Server has started"

echo "*****************************************************"
echo "String License Server with Configuration Service: $CONFIGSERVER_URI"
java -Dspring.cloud.config.uri=$CONFIGSERVER_URI \
	-Dspring.profiles.active=$PROFILE \
	-jar /usr/local/licensingservice/@project.build.finalName@.jar
