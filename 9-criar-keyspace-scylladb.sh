#!/bin/bash
set -e
set -x

docker compose exec scylla cqlsh -e "DROP KEYSPACE IF EXISTS piadas;"
docker compose exec scylla cqlsh -e "CREATE KEYSPACE piadas WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};"
docker compose exec scylla cqlsh -e "CREATE TABLE piadas.piadas (id uuid, texto text,PRIMARY KEY (id));"
