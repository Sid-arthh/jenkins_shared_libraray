def call(String imageUri) {
    def taskDefinition = ecsUtils(imageUri)
    echo "Generated Task Definition: ${taskDefinition}"
    sh "aws ecs register-task-definition --cli-input-json '${taskDefinition}' --region ${AWS_REGION}"
    // sh "aws ecs update-service --cluster $ECS_CLUSTER_NAME --service $ECS_SERVICE_NAME --force-new-deployment"
    def serviceExists = sh(script: "aws ecs describe-services --cluster $ECS_CLUSTER_NAME --services $ECS_SERVICE_NAME --region ${AWS_REGION}", returnStatus: true)

    if (serviceExists == 0) {
        // Update the existing service
        sh "aws ecs update-service --cluster $ECS_CLUSTER_NAME --service $ECS_SERVICE_NAME  --region ${AWS_REGION} --force-new-deployment"
    } else {
        // Create a new service
        sh "aws ecs create-service --cluster $ECS_CLUSTER_NAME --service-name $ECS_SERVICE_NAME --task-definition '${taskDefinition}' --region ${AWS_REGION}"
    }

}
