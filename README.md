# Tracing Distribuído com Quarkus

Descubra como rastrear seu código em tempo real com o Quarkus!
Em apenas alguns minutos você será capaz de identificar problemas em sistemas distribuídos de forma eficiente e sem dor
de cabeça.
Não perca esta oportunidade de impulsionar seus projetos de tecnologia para o próximo nível.

![Alt text](doc/images/sistema-completo.png?raw=true "Observability")

## Slides da Apresentaçāo: [Linktree](https://linktr.ee/palerique)

## Serviços:

| Serviço                                   | Descrição                                                | Endereço               |
|-------------------------------------------|----------------------------------------------------------|------------------------|
| [Serviço A](./quarkus-service-a)          | Serviço que produz mensagens no tópico do kafka          | http://localhost:8080  |
| [Serviço B](./quarkus-service-b)          | Serviço que consome mensagens do Kafka                   | http://localhost:8180  |
| [Serviço Piadas](./quarkus-service-piada) | Serviço que produz piadas                                | http://localhost:8280  |
| Jaeger UI (Tracing)                       | Interface para visualizar os tracings                    | http://localhost:16686 |
| Graylog (Logging)                         | Interface para visualizar as informações de Log          | http://localhost:9000  |
| Grafana (Metrics)                         | Interface para visualizar as informações de métricas     | http://localhost:3000  |
| Postgres                                  | Banco de dados relacional para testar o tracing com JDBC | localhost:5432         |
| Redpanda Console (Kafka)                  | Interface para visualizar os tópicos do Kafka            | http://localhost:9080  |
| Redpanda (Kafka)                          | Serviço que produz mensagens no tópico do Kafka          | localhost:19092        |
| OpenTelemetry Collector (Observability)   | Coleta todas as informações de Observabilidade           | localhost:4317         |
| Prometheus (Metrics)                      | Coleta as informações de métricas                        | http://localhost:9090  |
| Elasticsearch (Logging)                   | Indexa as informações de Log (Utilizado pelo Graylog)    | http://localhost:9200  |
| MongoDB (Logging)                         | Persiste as informações de Log (Utilizado pelo Graylog)  |                        |
