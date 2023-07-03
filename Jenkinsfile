pipeline {
    environment {
        registry = "rchidbaccouchi/examdevops"
        registryCredential = 'dockerhub_id'
        dockerImage = 'examdevops'
    }
    agent any
    stages {
        stage('Git') {
            steps {
                echo "Getting Project from Git";
                sh "rm -rf Achat_Devops_RAMAD"
                sh "git clone https://github.com/molka777/Achat_Devops_RAMAD.git"
                  }
            }
            
        stage('MVN Clean'){  
            steps {
                sh "mvn clean"
                  }
        }        
        
        stage('MVN Install'){
            steps {
                sh "mvn install"
                  }
        }     

        stage('MVN Compile'){
            steps {
                sh "mvn compile -DskipTests"
                  }
        }           
        
        stage('MVN Test'){
            steps {
                sh "mvn test"
                  }
        }   
       
        
        stage('MVN Package'){
            steps {
                sh "mvn package"
                  }
        }          
        
        stage('MVN SONARQUBE'){
            steps{
                sh 'mvn sonar:sonar \
  -Dsonar.projectKey=ExamDevOps \
  -Dsonar.host.url=http://192.168.163.67:9000 \
  -Dsonar.login=aa173785557cd7b2595a568c6e3a1553322fb77a'
                 }
        }    
        stage('MVN NEXUS'){
            steps {
                sh 'mvn deploy -DskipTests'
                  }
        }
        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build registry 
                }
            }
        }
        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Launch MySql & Spring simultaneously') {
            steps {
                sh 'docker-compose up -d'
            }
   }


    }
}
