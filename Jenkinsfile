pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/molka777/Achat_Devops_RAMAD-copie.git'
               
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
                sh 'mvn package -DskipTests'
                sh 'mvn install'
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}
