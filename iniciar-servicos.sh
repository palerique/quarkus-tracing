#!/bin/bash

# Function to open a new terminal and execute a command in a specific folder
open_terminal_in_folder() {
  local folder=$1
  local command=$2
  osascript -e "tell application \"Terminal\" to do script \"echo '$folder' && cd '$folder' && $command\""
}

docker compose up -d

sleep 60

./2-criar-input-no-graylog.sh

pushd quarkus-service-a || exit
open_terminal_in_folder "$(pwd)" "./gradlew clean quarkusDev"
popd || exit

pushd quarkus-service-piada || exit
open_terminal_in_folder "$(pwd)" "./gradlew clean quarkusDev"
popd || exit

docker compose logs -f --tail=0
