pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
        nodejs 'node18'
    }

    environment {
        PATH = "/usr/local/bin:${tool 'node18'}/bin:${tool 'jdk21'}/bin:${tool 'maven3'}/bin:/usr/bin:/bin:${env.PATH}"
        // DOCKER_COMPOSE_DIR Jenkins job veya global env üzerinden alınacak
        DOCKER_COMPOSE_DIR = "${env.DOCKER_COMPOSE_DIR}"
        PROJECT_DIR = "${WORKSPACE}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/cbesmhsyn96/E2ERocketChatApp_Mobile.git', branch: 'main'
            }
        }

        stage('Check Docker Compose File') {
            steps {
                script {
                    if (!fileExists("${DOCKER_COMPOSE_DIR}/docker-compose.yml")) {
                        error "docker-compose.yml bulunamadı: ${DOCKER_COMPOSE_DIR}"
                    }
                }
            }
        }

        stage('Prepare Rocket.Chat') {
            steps {
                dir(DOCKER_COMPOSE_DIR) {
                    sh '''
                    if docker ps -q -f name=rocketchat-test >/dev/null 2>&1; then
                        docker-compose down || true
                    fi
                    docker-compose up -d
                    '''
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
                    sh 'mvn clean test || true'
                }
            }
        }

//        stage('Results') {
//            steps {
//                junit '**/target/surefire-reports/*.xml'
//                archiveArtifacts artifacts: '**/target/*.html', fingerprint: true
//            }
//        }
    }

    post {
        always {
            dir(DOCKER_COMPOSE_DIR) {
                sh '''
                if docker ps -q -f name=rocketchat-test >/dev/null 2>&1; then
                    docker-compose down || true
                fi
                '''
            }
            echo 'Pipeline finished.'
        }
    }
}