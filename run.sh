#!/bin/bash
# 1 argument - module
# 2 argument - file to execute
# other arguments - program arguments
java -cp algs4.jar ./"$1"/src/main/java/"$2" "${@:3}"
