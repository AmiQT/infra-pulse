pipeline {
    agent any
    
    environment {
        APP_NAME = 'infra-pulse'
        DOCKER_REGISTRY = 'ghcr.io/amiqt'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_REGISTRY}/${APP_NAME}:${IMAGE_TAG} ."
                sh "docker tag ${DOCKER_REGISTRY}/${APP_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${APP_NAME}:latest"
            }
        }
        
        stage('Docker Push') {
            steps {
                // In production, you'd add: withCredentials([usernamePassword(credentialsId: 'ghcr-creds', ...)]) { ... }
                echo 'Pushing to GHCR...'
                // sh "docker push ${DOCKER_REGISTRY}/${APP_NAME}:${IMAGE_TAG}"
                // sh "docker push ${DOCKER_REGISTRY}/${APP_NAME}:latest"
            }
        }
        
        stage('Deploy to K8s') {
            steps {
                echo 'Deploying via Helm...'
                // sh "helm upgrade --install ${APP_NAME} ./helm --set image.tag=${IMAGE_TAG}"
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo "Successfully built and deployed version ${IMAGE_TAG}"
        }
        failure {
            echo "Build failed at stage ${env.STAGE_NAME}"
        }
    }
}
