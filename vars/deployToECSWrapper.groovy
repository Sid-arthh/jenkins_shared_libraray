def call() {
    withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        def ecrRepoName = 'your-ecr-repo-name'
        def imageUri = "${ecrRepoName}:latest"

        def ecsUtils = load 'ecsUtils.groovy'
        ecsUtils(imageUri)
    }
}
