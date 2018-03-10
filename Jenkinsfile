node {

  environment {
    SYNCER_HOSTS = 'archlinux'
  }
  stage('Clone repository') {
    checkout scm
  }

  def image

  stage('Build Docker image') {
    image = docker.build("johan1a/jenkins:${env.BUILD_ID}", "docker")
  }

  stage('Push Docker image') {
    image.push("johan1a/jenkins:${env.BUILD_ID}")
  }
}

