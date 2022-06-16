#!/usr/bin/env bash

GITEE_HOME=$HOME/.gitee-cli
GITEE_CONFIG=$GITEE_HOME/config
CLI_NAME=gitee-cli
if [ ! -d "${GITEE_HOME}" ]; then
  echo 'init gitee config'
  mkdir "$GITEE_HOME"
  touch "$GITEE_CONFIG"
fi
./bin/run.sh
./bin/build.sh
CLI_FILE=${GITEE_HOME}/${CLI_NAME}
if [ ! -f "${CLI_FILE}" ]; then
  echo "rm ${CLI_FILE}"
  rm "${CLI_FILE}"
fi
mv  ./target/${CLI_NAME} "${GITEE_HOME}"/
