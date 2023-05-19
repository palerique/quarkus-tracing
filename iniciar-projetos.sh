#!/usr/bin/env sh

EXTENSIONS='resteasy-reactive-jackson,
rest-client-reactive-jackson,
resteasy-reactive,
quarkus-hibernate-reactive-panache,
quarkus-opentelemetry,
smallrye-reactive-messaging-kafka,
micrometer-registry-prometheus,
logging-gelf'

quarkus create app br.com.palerique:quarkus-servico-a \
  --extension="$EXTENSIONS" \
  --gradle-kotlin-dsl

quarkus create app br.com.palerique:quarkus-servico-b \
  --extension="$EXTENSIONS" \
  --gradle-kotlin-dsl

quarkus create app br.com.palerique:quarkus-servico-piada \
  --extension="$EXTENSIONS" \
  --gradle-kotlin-dsl
