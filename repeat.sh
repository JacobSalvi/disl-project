#!/bin/bash

if [ "$#" -ne 1 ]; then
	echo "Usage: $0 <Number of repetition>"
	exit;
fi

rm -f -- results.txt

for (( i = 3; i < 8; i++ )); do
    for (( j = 0; j < $1; j++ )); do
        source ./disl.sh "$i"
        sleep 1
    done
done
