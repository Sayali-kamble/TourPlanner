pipeline {
    agent any

    environment {
        SPRING_PROFILES_ACTIVE = 'prod'
        MAVEN_HOME = tool name: 'Maven', type: 'ToolLocation'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build Backend') {
            steps {
                script {
                    // Backend build using Maven
                    sh "'${MAVEN_HOME}/bin/mvn' clean install"
                }
            }
        }
        stage('Run Backend Tests') {
            steps {
                script {
                    // Running JUnit and Mockito tests for backend
                    sh "'${MAVEN_HOME}/bin/mvn' test"
                }
            }
        }
        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    // Building the React application
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        stage('Run Frontend Tests') {
            steps {
                dir('frontend') {
                    // Running frontend tests (using Jest or other test runners)
                    sh 'npm test -- --coverage'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Deploy step (adjust according to your deployment setup)
                    echo 'Deploying the application'
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests succeeded!'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}


