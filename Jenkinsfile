pipeline {
    agent any

    environment {
        MAVEN_HOME = 'D:\\apache-maven-3.9.9'  
        NODE_HOME = 'D:\\nodejs' 
        PATH = "${MAVEN_HOME}\\bin;${NODE_HOME};C:\\Windows\\System32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0;"
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
                    dir('src/main/webapp/frontend') {
                    bat 'npm install'
                    }
                }
            }
        }

        stage('Build React App') {
            steps {
                script {
                    dir('src/main/webapp/frontend') {
                    withEnv(["CI=false", "ESLINT_NO_DEV_ERRORS=true"]) { 
                    bat 'npm run build'
                     }
                    }
                }
            }
        }

        stage('Build Spring Boot Project') {
            steps {
                script {
                    bat "\"%MAVEN_HOME%\\bin\\mvn\" clean install -DskipTests"
                }
            }
        }

        stage('Run JUnit Tests - Spring Boot') {
            steps {
                script {
                    bat "\"%MAVEN_HOME%\\bin\\mvn\" test"
                }
            }
        }

        stage('Run React Tests') {
            steps {
                script {
                    dir('src/main/webapp/frontend') {
                    bat 'npm test -- --watchAll=false'
                    }
                }
            }
        }
    }

    post {
        always {
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
