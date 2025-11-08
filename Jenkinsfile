pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build') {
      steps { sh 'mvn -B -DskipTests clean package' }
    }
    stage('Unit Tests') {
      steps { sh 'mvn -B test' }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }
    stage('Docker Build') {
      steps { echo 'Add docker build/push steps as needed.' }
    }
  }
}
