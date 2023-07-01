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


                // Run Maven on a Unix agent.
                sh "mvn compile"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
         stage('mvn test unitaire  ') {
                    steps {

                        // Run Maven on a Unix agent.
                        sh "mvn test"

                        // To run Maven on a Windows agent, use
                        // bat "mvn -Dmaven.test.failure.ignore=true clean package"
                    }

                }
                 stage('SonarQ ') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
                         stage('Nexus ') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn deploy -DskipTests"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

        }
            stage('Build Docker Image') {
                    steps {
                        script {
                                sh """ docker build -t medalibm/achat ."""


                        }
                    }
                }

               stage('push to DockerHub') {
                    steps{

                        sh """ docker push  medalibm/achat """

                    }
                }



    }
}
