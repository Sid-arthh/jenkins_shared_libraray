def call(String imageUri) {
    def taskDefinition = ecsUtils(imageUri)
    echo "Generated Task Definition: ${taskDefinition}"
    sh "aws ecs register-task-definition --cli-input-json '${taskDefinition}' --version 10 --region ${AWS_REGION}"
    def serviceExists = sh(script: "aws ecs describe-services --cluster $ECS_CLUSTER_NAME --services $ECS_SERVICE_NAME --region ${AWS_REGION}", returnStatus: true)
    echo "Value of it is ${serviceExists}"
    if (serviceExists != 0) {

        sh "aws ecs update-service --cluster $ECS_CLUSTER_NAME --service $ECS_SERVICE_NAME  --region ${AWS_REGION} --desired-count 1 --force-new-deployment"
    } else {

        sh "aws ecs create-service --cluster $ECS_CLUSTER_NAME --service-name $ECS_SERVICE_NAME --task-definition '${taskDefinition}' --desired-count 1 --region ${AWS_REGION}"
    }

}
