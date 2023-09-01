def buildApp() {
    // stage('Build_App') {
    //     steps {
            script {
                dir('app') {
    
                sh "ls"
                sh "mvn clean package"
                }
            }
    //     }
    // }
}

def DockerBuildPushToECR() {
    // stage('Building Image') {
    //     steps {
    script {
     dir('app') {
        withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: 'AWS CRED',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        // def ecrRepoName = 'your-ecr-repo-name'
        
        
    
            sh "ls"
            sh "docker build -t $ECR_REPO_NAME ."
        //         }
        //     }
        // }
    // }
    
    // script {
    //     dir('app') {
            aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/e5i0e5h0"
            docker tag $ECR_REPO_NAME:latest public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest
            sh "docker push public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
    //     }
    // }
            return "docker push public.ecr.aws/e5i0e5h0/$ECR_REPO_NAME:latest"
}
}
}
}
