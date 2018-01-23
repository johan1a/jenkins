#FROM jenkins:2.60.3-alpine
FROM jenkins:2.60.3
#-alpine

USER root
#RUN apk add --no-cache sudo
RUN apt-get update \
      && apt-get install -y sudo libltdl7 \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

USER jenkins
COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN  xargs /usr/local/bin/install-plugins.sh /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/plugins.txt | tr '\n' ' ')
