#!/bin/bash

function run(){
  clear
  echo "Compiling and running Best Route App..."
  mvn clean install -q -Dmaven.test.skip=true
  mvn exec:java -Dexec.mainClass="br.ignicaodigital.bestroute.main.Main"
}

function test(){
  clear
  echo "Running automated tests for Best Route App..."
  mvn clean install test -q
}

function uninstall(){
  clear
  echo "Deleting files related to Best Route App..."
  mvn clean -q
  clear
}

$@
