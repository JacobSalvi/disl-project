#!/bin/sh

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
./runInstrumented.sh ex"$1".Main