FROM jenkins/jenkins

USER root
RUN apt-get update \
      && apt-get install -y sudo libltdl7 \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

RUN groupadd docker
RUN usermod -aG docker jenkins

USER jenkins

ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false \
    -Djava.awt.headless=true

ENV JENKINS_HOST_HOME=/data/jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY init.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY seed.groovy /usr/share/jenkins/seed.groovy


