#!/bin/sh
docker rm jenkins
docker run -p 8080:8080 \
      -v /var/run/docker.sock:/var/run/docker.sock:ro \
      -v /usr/bin/docker:/usr/bin/docker \
      -v /home/johan/.jenkins_ssh:/var/jenkins_home/.ssh_host/ \
      --restart=always \
      --network=host \
      --group-add 994 \
      --name jenkins \
      johan1a/jenkins
