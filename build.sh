#!/bin/bash
set -e

echo "[BUILD] Compiling library..."
javac -d out \
    -Xlint:unchecked \
	$(find src/ -type f -name "*.java")

echo "[BUILD] Packaging library..."
jar --create \
	--file jars/JeXmlReader.jar \
	-C out .

echo "[BUILD] Compiling test program..."
cd Test	
javac -d out/ \
    -Xlint:unchecked \
	--module-path "out:../jars" \
	--add-modules JeXmlReader \
	$(find src/ -type f -name "*.java")

echo "[BUILD] Packaging test program..."
jar --create \
	--main-class main.Main \
	--file jars/TestApp.jar \
	-C out . \
	-C res/main .

echo "[BUILD] Done!"
