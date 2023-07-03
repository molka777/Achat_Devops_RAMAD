pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
               
            }
        }
    stage('MVN Clean') {
            steps {
               sh 'mvn clean'
               
            }
        }   
    
    stage('MVN Compile') {
            steps {
                
             sh'mvn compile'
               
            }
        }
     stage('MVN SonarQube') {
            steps {
               sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=damino'
               
            }
        } 
    stage('Unit Test ') {
            steps {
                sh 'mvn test'
            }
        }

    stage('Nexus') {
            steps {
                sh "mvn package -DskipTests"
                sh "mvn install"
                sh "mvn deploy -DskipTests"
            }
        }

        stage('Build Image (spring)') {
            steps {
                sh 'docker build -t damino15/achat:latest .'
            }
        }
    }

      post {
    always {
      // Send email regardless of build result
      emailext body: "${currentBuild.currentResult}",
               subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
               to: "mohamedamine.askri@esprit.tn",
               from: "mohamedamine.askri@esprit.tn"
            }
     }
}
