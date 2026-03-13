#!/bin/bash

cd Test
echo "Running Application..."
java -jar \
	--module-path "out:../jars" \
	--add-modules JeXmlReader \
	jars/TestApp.jar
echo "Application exited with exit code $?"
