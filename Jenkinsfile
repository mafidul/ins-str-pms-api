pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
               echo 'Building the App'
                mvn clean install
            }
        }
        stage('Test') { 
            steps {
              echo 'Testing the App'
                  mvn package install
            }
        }
        stage('Deploy') { 
            steps {
              echo 'Deploy the App'
            }
        }
    }
}
