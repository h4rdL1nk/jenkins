pipeline {
    agent {
                label 'worker'
    }
    parameters {
    }
    options {
        timestamps()
        disableConcurrentBuilds()
    }
    stages {
        stage('Show data'){
            steps{
                echo "Printing data ..."
            }
        }
    }
    post {
                failure {
                        emailext(
                                from: "jenkins-ci@app.madisonmk.com",
                                to: "luismiguel.saez@madisonmk.com",
                                mimeType: 'text/html',
                                subject: "[${currentBuild.currentResult}] ${BUILD_DISPLAY_NAME} ${JOB_NAME}",
                                body: "<br>Finalizado ${JOB_NAME} ${BUILD_NUMBER}<br>Nodo:${NODE_NAME}",
                                attachLog: true
                                )
                }
    }
}
