pipeline {
    agent any

    environment {
        AWS_REGION = 'eu-north-1'
        S3_BUCKET = 'awstourbucket'
        INSTANCE_USER = 'ubuntu'
        INSTANCE_IP = '16.170.208.135'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/Sayali-kamble/TourPlanner.git'
            }
        }

        stage('Build Backend') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(credentials: ['ec2_ssh']) {
                    sh """
                        scp -o StrictHostKeyChecking=no target/*.jar ${INSTANCE_USER}@${INSTANCE_IP}:/home/ubuntu/app.jar
                        ssh ${INSTANCE_USER}@${INSTANCE_IP} 'sudo systemctl restart myapp'
                    """
                }
            }
        }
    }
}

