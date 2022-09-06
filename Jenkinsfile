pipeline {
  agent any
  stages {
    stage('Git') {
      steps {
        git(url: 'https://github.com/AtefMaddouri/Spring-Security-JWT', branch: 'main', credentialsId: '	Git-Credentials')
      }
    }

    stage('Build') {
      steps {
        build 'mvn -version'
      }
    }

  }
}