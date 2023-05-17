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

#quarkus create app br.com.palerique:quarkus-servico-b \
#  --extension="$EXTENSIONS" \
#  --gradle-kotlin-dsl
#
#quarkus create app br.com.palerique:quarkus-servico-piada \
#  --extension="$EXTENSIONS" \
#  --gradle-kotlin-dsl

curl -H "Content-Type: application/json" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "X-Requested-By: curl" -X POST -v -d \
'{"title":"udp input","configuration":{"recv_buffer_size":262144,"bind_address":"0.0.0.0","port":12201,"decompress_size_limit":8388608},"type":"org.graylog2.inputs.gelf.udp.GELFUDPInput","global":true}' \
http://localhost:9000/api/system/inputs
