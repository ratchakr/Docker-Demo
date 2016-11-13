#!/bin/bash
#echo $SLEEP_TIME
echo "Starting script ..........."
echo "Wait for 10 sec"
sleep 10
java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
echo "bye bye"
#sleep $SLEEP_TIME