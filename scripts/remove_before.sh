#!/bin/bash
find /opt/codedeploy-agent/deployment-root/* -maxdepth 3 -type 'd' | grep -v
$(stat -c '%Y:%n' /opt/codedeploy-agent/deployment-root/* | sort -t: -n | tail -1 | cut -d: -f2- | cut -c 3-) | xargs rm -rf

sudo rm -rf /home/ec2-user/*