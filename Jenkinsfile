pipeline {
    agent any
    stages {
        stage ('Build backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }

        stage ('Unit tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Deploy backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'loginTomcat', path: '', url: 'http://192.168.10.13:8080')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }

        stage ('API test') {
            steps {
                dir('api-test') {
                    git credentialsId: 'githubLogin', url: 'https://github.com/antonioiagolg/tasks-api-test'
                    sh 'mvn test'
                }
            }
        }
    }
}