pipeline {
    agent any

    stages {


        stage('git') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url:'https://github.com/molka777/Achat_Devops_RAMAD.git'

            }

        }
         stage('Compiler') {
            steps {

                sh "mvn compile"

            }

        }
         stage('mvn test unitaire  ') {
                    steps {

                        sh "mvn test"

                    }

                }
                 stage('test with SonarQ ') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
                stage('mvn package -DskipTests'){
            steps {
                sh "mvn package"
                  }
        } 
                         stage('Upload to Nexus ') {
            steps {

                sh "mvn deploy -DskipTests"

            }

        }
            stage('Build Docker Image') {
                    steps {
                        script {
                                git branch: 'Dali-Boumnijel', url: 'https://github.com/molka777/Achat_Devops_RAMAD.git'
                                sh """ docker build -t medalibm/achat ."""

                        }
                    }
                }

               stage('push to DockerHub') {
                    steps{
                        sh 'docker login -u "medalibm" -p "daliinfo2222" docker.io'
                        sh """ docker push  medalibm/achat """

                    }
                }
                 stage('Docker-compose up -d ') {
                    steps{
                        sh 'docker-compose down'
                        sh 'docker-compose up -d'
                    }
                }
    }
}
