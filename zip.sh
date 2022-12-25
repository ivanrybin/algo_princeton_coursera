#!/bin/bash
cd ./"$1"/src/main/java || exit
zip "$2.zip" "${@:3}"
mv "$2.zip" ../../../
