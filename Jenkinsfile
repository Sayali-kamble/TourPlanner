pipeline {
    agent any

    environment {
        MAVEN_HOME = 'D:\\apache-maven-3.9.9'  
        NODE_HOME = 'D:\\nodejs' 
        PATH = "${MAVEN_HOME}\\bin;${NODE_HOME}\\bin${env.PATH};C:\\Windows\\System32;C:\\Windows\\System32\\OpenSSH"

        AWS_S3_BUCKET = 'awstripbucket'  
        EC2_USER = 'ubuntu'
        EC2_HOST = '13.50.4.93'

        
        MONGO_URI = credentials('MONGO_ATLAS_URI')
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

        stage('Deploy React to S3') {
            steps {
                script {
                    dir('src/main/webapp/frontend/build') {
                        bat '"C:\\Program Files\\Amazon\\AWSCLIV2\\aws.exe" s3 sync . s3://%AWS_S3_BUCKET% --region eu-north-1 --delete'
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

        stage('Deploy Spring Boot to EC2') {
            steps {
                script {
                    bat """
                    echo Stopping any running application on EC2...
                    ssh -o StrictHostKeyChecking=no -i "C:\\SSHKeys\\travelling.pem" %EC2_USER%@%EC2_HOST% "sudo pkill -f 'tourplanner.jar' || true"

                    echo Uploading JAR file to EC2...
                    scp -o StrictHostKeyChecking=no -i "C:\\SSHKeys\\travelling.pem" target/tourplanner-0.0.1-SNAPSHOT.jar %EC2_USER%@%EC2_HOST%:/home/ubuntu/tourplanner.jar

                    echo Starting application...
                    ssh -o StrictHostKeyChecking=no -i "C:\\SSHKeys\\travelling.pem" %EC2_USER%@%EC2_HOST% "export MONGO_URI=\\"$MONGO_URI\\" && nohup java -jar /home/ubuntu/tourplanner.jar > /home/ubuntu/tourplanner.log 2>&1 &"
                    """
                }
            }
        }
    }  
    post {
        always {
            echo 'Cleaning up...'
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
