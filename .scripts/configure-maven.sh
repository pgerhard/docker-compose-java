#!/bin/bash

cp ./.github/settings-ci.xml $HOME/.m2/settings.xml

echo "Configured Maven Settings"
cat $HOME/.m2/settings.xml