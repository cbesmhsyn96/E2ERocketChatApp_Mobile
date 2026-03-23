pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
        nodejs 'node18'
    }

    environment {
        PATH = "${tool 'node18'}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/cbesmhsyn96/E2ERocketChatApp_Mobile.git', branch: 'main'
            }
        }

        stage('Install Appium') {
            steps {
                sh 'npm install -g appium'
            }
        }

        stage('Install Gauge') {
            steps {
                sh 'npm install -g @getgauge/cli'
                sh 'gauge -v'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/*.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}