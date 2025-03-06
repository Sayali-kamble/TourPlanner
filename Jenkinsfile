pipeline {
    agent any

    environment {
        MAVEN_HOME = 'D:\\apache-maven-3.9.9'  
        NODE_HOME = 'D:\\nodejs' 
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Sayali-kamble/TourPlanner.git'  
            }
        }

        stage('Install Dependencies - React') {
            steps {
                script {
                    // Install Node.js dependencies for React
                    sh 'npm install'  // This assumes package.json is in the root or frontend folder
                }
            }
        }

        stage('Build React App') {
            steps {
                script {
                    // Build React app
                    sh 'npm run build'
                }
            }
        }

        stage('Build Spring Boot Project') {
            steps {
                script {
                    // Install Maven dependencies and build the Spring Boot project
                    sh "${MAVEN_HOME}/bin/mvn clean install -DskipTests"
                }
            }
        }

        stage('Run JUnit Tests - Spring Boot') {
            steps {
                script {
                    // Run JUnit tests for Spring Boot
                    sh "${MAVEN_HOME}/bin/mvn test"
                }
            }
        }

        stage('Run React Tests') {
            steps {
                script {
                    // Run unit tests for React frontend (if applicable)
                    sh 'npm test -- --watchAll=false'
                }
            }
        }
    }

    post {
        always {
            // Clean up or notifications
            echo 'Cleaning up...'
        }
        success {
            echo 'Build and tests passed!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
