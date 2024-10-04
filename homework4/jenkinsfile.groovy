pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-credentials')
        IMAGE_NAME = 'gipsyone/devx/simple-nginx-app'
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository (assumes a Git repository)
                git branch: 'main', url: 'https://github.com/NourHajYahia/Devx.git'            
            }
        }

        stage('Navigate to Inner Directory') {
            steps {
                // Change to the inner directory where the Dockerfile and app files are located
                dir('homework4/') {
                    script {
                        // Optionally, list the contents of the directory for verification
                        sh 'ls -la'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Navigate to the inner directory and build the Docker image
                    dir('homework4/') {
                        dockerImage = docker.build("${IMAGE_NAME}")
                    }
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        // no-op, just login
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        dockerImage.push('simple-nginx-app')
                    }
                }
            }
        }
    }

}
