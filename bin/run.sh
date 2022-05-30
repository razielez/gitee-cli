#!/bin/bash
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar ./target/gitee-cli-0.1.jar