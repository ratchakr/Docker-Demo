# Version 0.0.1

# FROM node:4
FROM ubuntu:14.04
RUN apt-get update -y
RUN apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_4.x | bash -
RUN apt-get install -y nodejs

COPY server.js /

EXPOSE 8081

CMD node server.js