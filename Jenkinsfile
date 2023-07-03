pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Getting project from git...'
                git branch: 'Molka-Allani',
                    url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean compile test'
            }
        }
         stage('mvn package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('mvn install') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }
        stage('SonarQube Scan') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=Molka-Achat -Dsonar.host.url=http://192.168.43.54:9000 -Dsonar.login=dc5819c69545a39e505a3f130f972b9de34b2168'
            }
        }
        stage('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Building Image') {
            steps {
                sh 'docker build -t molka777/achat:10 .'
            }
        }
        stage('Deploying Image') {
            steps {
                sh 'docker login -u "molka777" -p "dockerhub" docker.io'
                sh 'docker push molka777/achat:10'
            }
        }
        stage('Docker-compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
         stage('Docker-compose logs analyzis') {
            steps {
                sh 'docker-compose logs'
            }
        }
    }
}
