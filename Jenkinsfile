pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        node {
            stage('Applitools build') {
                Applitools() {
                    sh 'mvn clean test -Dtest=HelloWorld'
                }
            }
        }

    }
}