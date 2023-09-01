def buildApp() {
            script {
                sh "ls"
                sh "mvn clean package"
            }
}

def DockerBuildPushToECR() {
    script {
            sh "ls"
            sh "docker build -t $ECR_REPO_NAME ."
            sh "aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/e5i0e5h0"
            sh "docker tag $ECR_REPO_NAME:latest public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
            sh "docker push public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
            return "public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
}
}
