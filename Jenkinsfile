pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
        nodejs 'node18'
    }

    environment {
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
                withEnv(["PATH=/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:${env.PATH}"]) {
                    dir(DOCKER_COMPOSE_DIR) {
                        sh '''
                        if docker ps -q -f name=rocketchat-test >/dev/null 2>&1; then
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
                withEnv(["PATH=/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:${env.PATH}"]) {
                    sh 'npm install -g appium'
                }
            }
        }

        stage('Install Gauge') {
            steps {
                withEnv(["PATH=/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:${env.PATH}"]) {
                    sh 'npm install -g @getgauge/cli'
                    sh 'gauge -v'
                    sh 'java -version'
                }
            }
        }

        stage('Run Tests') {
            steps {
                withEnv(["PATH=/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:${env.PATH}"]) {
                    dir(PROJECT_DIR) {
                        sh 'mvn clean test'
                    }
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
            withEnv(["PATH=/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:${env.PATH}"]) {
                dir(DOCKER_COMPOSE_DIR) {
                    sh '''
                    if docker ps -q -f name=rocketchat-test >/dev/null 2>&1; then
                        docker-compose down
                    fi
                    '''
                }
            }
            echo 'Pipeline finished.'
        }
    }
}