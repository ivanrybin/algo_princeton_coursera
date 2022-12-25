#!/bin/bash
cd ./src/main/java || exit
javac -cp ../../../../algs4.jar ./*.java
java -cp ../../../../algs4.jar:. PercolationStats "$@"
rm ./*.class
