pipeline {
    
    agent any


    stages {
        stage('Get prject from GIT') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', credentialsId: 'd9c3df91-0beb-4636-ae2a-8d1724af1e38', url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
            }

            post {
                success {
                    echo 'Récupération done'
                }
            }
        }
        
        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Maven Compile'){
            steps {
                sh 'mvn compile -DskipTests'
            }
        }
        
        stage('Les tests unitaires') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('SonarQube Tests') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
        
        stage('Déposer le livrable sur Nexus') {
            steps {
                sh 'mvn package -DskipTests'
                sh 'mvn install'
                sh 'mvn deploy -DskipTests'
            }
        }
        
        stage('Build de l’image (partie spring)') {
            steps {
                sh 'docker build -t ratatouka/achat:latest .'
            }
        }
        
        stage('Déposer l\'image créée sur DockerHub') {
            steps {
                sh 'docker login -u "ratatouka" -p "Bloodytears123+" docker.io'
                sh 'docker push ratatouka/achat:latest'
            }
        }
        
        stage('Lancer simultanément les images avec docker-compose') {
            steps {
                sh 'git checkout Amir-Ayed'
                git branch: 'Amir-Ayed', credentialsId: 'd9c3df91-0beb-4636-ae2a-8d1724af1e38', url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
                sh 'docker-compose up -d'
            }
        }
    }
}
