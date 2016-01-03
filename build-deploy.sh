#!/bin/sh

find ./  -name "pom.xml" -exec sed -i "s/<version>\${project.framework.version}/<version>$1/g" {} \;

mvn -Dversion=$1 -Dmaven.test.skip=true clean deploy 
