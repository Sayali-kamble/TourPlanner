pipeline {
    agent any
    
    environment {
        // Define environment variables for tools and paths
        MAVEN_HOME = tool name: 'Maven', type: 'Tool'
        JDK_HOME = tool name: 'JDK 17', type: 'Tool'  
        NODE_HOME = tool name: 'NodeJS', type: 'Tool'
    }

    stages {
        
        
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        
        stage('Install React Dependencies') {
            steps {
                script {
                    // Install Node.js dependencies
                    sh 'npm install --prefix frontend'
                }
            }
        }

        
        stage('Build React') {
            steps {
                script {
                    // Build the React app
                    sh 'npm run build --prefix frontend'
                }
            }
        }

       
        stage('Test Backend') {
            steps {
                script {
                    // Run Maven tests (JUnit, Mockito)
                    sh "${MAVEN_HOME}/bin/mvn clean test -DskipTests=false"
                }
            }
        }

       
        stage('Build Backend') {
            steps {
                script {
                    // Build the Spring Boot application
                    sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests=true"
                }
            }
        }

        
        stage('Integration Test') {
            steps {
                script {
                   
                    sh "${MAVEN_HOME}/bin/mvn verify"
                }
            }
        }

        
        stage('Deploy') {
            steps {
                script {
                    
                    sh "docker build -t my-backend -f backend/Dockerfile ."
                    sh "docker run -d -p 8080:8080 my-backend"
                    
                   
                    sh "cp -R frontend/build/* /path/to/deployment/directory"
                }
            }
        }

       
        stage('Clean Up') {
            steps {
                script {
                    cleanWs()
                }
            }
        }
    }

    post {
        success {
            echo "Build and deploy were successful!"
        }
        failure {
            echo "Build or deploy failed. Please check the logs."
        }
        always {
            
            junit '**/target/test-*.xml' // Example path for JUnit test results
        }
    }
}
