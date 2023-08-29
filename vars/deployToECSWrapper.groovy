def call() {
    withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: 'AWS CRED',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        // def ecrRepoName = 'your-ecr-repo-name'
        def imageUri = "public.ecr.aws/nginx/nginx:latest"
        
        deployToECS(imageUri)
    }
}
