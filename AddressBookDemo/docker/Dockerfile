FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD contactsdemo-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ADD run_demo.sh .
RUN chmod +x run_demo.sh
CMD sh run_demo.sh
