#!/usr/bin/env bash
GITEE_HOME=$HOME/.gitee-cli
GITEE_CONFIG=$GITEE_HOME/config
if [ ! -d ${GITEE_HOME} ]; then
  echo 'init gitee config'
  mkdir $GITEE_HOME
  touch $GITEE_CONFIG
fi
./bin/run.sh
./bin/build.sh
cp ./target/gitee-cli $GITEE_HOME/