#!/bin/bash

count=5000

echo "--------------------------------------------------"
for ((i = 1; i <= $count; i++)); do
  echo "Request $i"
  curl --location 'http://localhost:8080/iniciar'
  echo
  echo "--------------------------------------------------"
done

wait
echo "All requests completed."
