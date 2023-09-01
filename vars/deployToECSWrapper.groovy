def call() {
    withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: 'AWS CRED',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        // def ecrRepoName = 'your-ecr-repo-name'
        appBuilder.buildApp()
        appBuilder.DockerBuildPushToECR(ECR_REPO_NAME, GIT_COMMIT)
        def imageUri = "nginx:latest"
        
        deployToECS(imageUri)
    }
}
