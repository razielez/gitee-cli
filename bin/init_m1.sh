#!/bin/zsh
./bin/init.sh
sudo xattr -d -r com.apple.quarantine  "$HOME"/.gitee-cli/gitee-cli
