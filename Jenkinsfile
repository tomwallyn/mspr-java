pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World 2'
                echo "${env.BUILD_ID}"
            }
        }
        stage('Example Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}
