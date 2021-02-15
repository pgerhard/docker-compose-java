#!/bin/bash

echo "Maven version"
mvn --version

echo "Running Maven project build"
mvn -V -B -Pci clean deploy