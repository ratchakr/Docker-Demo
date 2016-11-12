# Version 0.0.1

FROM postgres:9.4.3

ADD setupRemoteConnections.sh /docker-entrypoint-initdb.d/setupRemoteConnections.sh
RUN chmod 755 /docker-entrypoint-initdb.d/setupRemoteConnections.sh
ADD setup-database.sh /docker-entrypoint-initdb.d/
RUN chmod 755 /docker-entrypoint-initdb.d/setup-database.sh
