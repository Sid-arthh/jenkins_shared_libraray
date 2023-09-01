def call(imageUri) {
    withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        credentialsId: 'AWS CRED',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        deployToECS(imageUri)
    }
}
