#!/usr/bin/env sh

pushd quarkus-service-b
./gradlew quarkusDev &
popd

pushd quarkus-service-piada
./gradlew quarkusDev &
popd

pushd quarkus-service-scylladb
./gradlew quarkusDev &
popd

pushd quarkus-service-a
./gradlew quarkusDev &
popd
