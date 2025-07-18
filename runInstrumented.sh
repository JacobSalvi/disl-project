#!/bin/bash


if [ "$#" -ne 1 ]; then
	echo "Usage: $0 <Name of main class>"
	exit;
fi

AGENT_EXT=.so

#Checks if OS is MacOS
#if [ `uname -s` == "Darwin" ]; then
#    AGENT_EXT=.jnilib
#    echo "you shouldn't see this!!!!!!!!"
#fi

# ask if disl compile by default for the system that compile it
#ARCH=x86_64

#Check if architecture is aarch64

#if [ `uname -p` = "aarch64" ]; then
#    ARCH=aarch64
#fi

#AGENT_FLAGS=

#JAVA_VERSION=$("$JAVA_HOME/bin/java" -version 2>&1 | head -1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1)


AGENT_FLAGS="$AGENT_FLAGS --patch-module java.base=lib/disl-bypass.jar --add-exports java.base/ch.usi.dag.disl.dynamicbypass=ALL-UNNAMED"
echo "agent flags"
echo "$AGENT_FLAGS"

$JAVA_HOME/bin/java --enable-preview -agentpath:lib/libdislagent$AGENT_EXT $AGENT_FLAGS -Xbootclasspath/a:lib/disl-bypass.jar:build/profiler.jar -cp build/app.jar -noverify $1
