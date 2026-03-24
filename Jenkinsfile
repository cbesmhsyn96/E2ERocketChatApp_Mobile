pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
        nodejs 'node18'
    }

    environment {
        PATH = "${tool 'node18'}/bin:${env.PATH}"
        DOCKER_COMPOSE_DIR = "${WORKSPACE}/rocketchat-test"
        PROJECT_DIR = "${WORKSPACE}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/cbesmhsyn96/E2ERocketChatApp_Mobile.git', branch: 'main'
            }
        }

        stage('Prepare Rocket.Chat') {
            steps {
                script {
                    dir(DOCKER_COMPOSE_DIR) {
                        sh '''
                        if [ -n "$(docker ps -q -f name=rocketchat-test)" ]; then
                            docker-compose down
                        fi
                        docker-compose up -d
                        '''
                    }
                }
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
                sh 'java -version'
            }
        }

        stage('Run Tests') {
            steps {
                dir(PROJECT_DIR) {
                    sh 'mvn clean test'
                }
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
            script {
                dir(DOCKER_COMPOSE_DIR) {
                    sh 'docker-compose down'
                }
            }
            echo 'Pipeline finished.'
        }
    }
}