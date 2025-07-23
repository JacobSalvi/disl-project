#!/bin/bash

if [ "$#" -ne 1 ]; then
	echo "Usage: $0 <Number of exercise>"
	exit;
fi

ant clean
echo "> cleaned"

ant -Ddislclass=ex"$1".Instrumentation
./startDiSLServer.sh
sleep 2
echo "> server started"
echo "> running ex $1"
start_time=$(($(date +%s%N)))
./runInstrumented.sh ex"$1".Main
end_time=$(($(date +%s%N)))
elapsed="$(($end_time-$start_time))"
echo "ex$1 $elapsed ns" >> results.txt
