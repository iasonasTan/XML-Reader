#!/bin/bash
set -e

./build.sh

cd Test
echo "[RUN] Running Application..."
java -jar \
	--module-path "out:../jars" \
	--add-modules JeXmlReader \
	jars/TestApp.jar \
	$(cat ../params.txt)
echo "[RUN] Application exited with exit code $?."
