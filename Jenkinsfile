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

            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }

        stage('Maven Compile'){
            steps {
                sh 'mvn compile -DskipTests'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('Les tests unitaires') {
            steps {
                sh 'mvn test'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('SonarQube Tests') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('Déposer le livrable sur Nexus') {
            steps {
                sh 'mvn package -DskipTests'
                sh 'mvn deploy -DskipTests'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('Build de l’image (partie spring)') {
            steps {
                sh 'docker build -t ratatouka/achat:latest .'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('Déposer l\'image créée sur DockerHub') {
            steps {
                sh 'docker login -u "ratatouka" -p "Bloodytears123+" docker.io'
                sh 'docker push ratatouka/achat:latest'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
        
        stage('Lancer simultanément les images avec docker-compose') {
            steps {
                sh 'git checkout Amir-Ayed'
                git branch: 'Amir-Ayed', credentialsId: 'd9c3df91-0beb-4636-ae2a-8d1724af1e38', url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
                sh 'docker-compose up -d'
            }
            post {
                failure {
                      // Send email regardless of build result
                  emailext body: "${currentBuild.currentResult}", // Use ${currentBuild.currentResult} to get the build result
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "ben.ayed.amir115@gmail.com",
               from: "ben.ayed.amir115@gmail.com"
            }
          }
        }
    }
}
