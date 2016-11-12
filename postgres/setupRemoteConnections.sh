#!/bin/bash

sed -i '/host    all             all             127.0.0.1\/32            trust/a host    all             all             127.0.0.1\/32            trust' /var/lib/postgresql/data/pg_hba.conf

