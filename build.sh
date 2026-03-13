#!/bin/bash
set -e

echo "Compiling library..."
javac -d out \
    -Xlint:unchecked \
	$(find src/ -type f -name "*.java")

echo "Packaging library..."
jar --create \
	--file jars/JeXmlReader.jar \
	-C out .

echo "Compiling test program..."
cd Test	
javac -d out/ \
    -Xlint:unchecked \
	--module-path "out:../jars" \
	--add-modules JeXmlReader \
	$(find src/ -type f -name "*.java")

echo "Packaging test program..."
jar --create \
	--main-class main.Main \
	--file jars/TestApp.jar \
	-C out . \
	-C res/main .

echo "Done!"
