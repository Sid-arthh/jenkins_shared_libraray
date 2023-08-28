def call(String imageUri) {
    def taskDefinition = ecsUtils(imageUri)
    echo "Generated Task Definition: ${taskDefinition}"
    sh "aws ecs register-task-definition --cli-input-json '${taskDefinition}'"
    sh "aws ecs update-service --cluster $ECS_CLUSTER_NAME --service $ECS_SERVICE_NAME --force-new-deployment"
}
