pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build') {
            steps {
                sh 'ls'
            }
        }
        node {
            stage('Applitools build') {
                Applitools() {
                    sh 'mvn clean test -Dtest=HelloWorld'
                }
            }
        }

    }
}