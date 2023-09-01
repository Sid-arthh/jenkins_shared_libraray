def buildApp() {
    // stage('Build_App') {
    //     steps {
    //         script {
    //             dir('app') {
            sh "cd app"
            sh "mvn clean package"
    //             }
    //         }
    //     }
    // }
}

def DockerBuildPushToECR() {
    // stage('Building Image') {
    //     steps {
    //         script {
    //             dir('app') {
            sh "cd app"
            sh "docker build -t $ECR_REPO_NAME ."
        //         }
        //     }
        // }
    // }
    
    // script {
    //     dir('app') {
            sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/e5i0e5h0"
            sh "docker tag $ECR_REPO_NAME public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
            sh "docker push public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
    //     }
    // }
            return "public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
}
