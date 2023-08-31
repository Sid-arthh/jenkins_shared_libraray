def call(String imageUri) {
    def taskDefinition = ecsUtils(imageUri)
    echo "Generated Task Definition: ${taskDefinition}"
    // sh "aws ecs register-task-definition --cli-input-json '${taskDefinition}' --region ${AWS_REGION}"
    def taskDefinitionArn = sh(
        script: "aws ecs register-task-definition --cli-input-json '${taskDefinition}' --query 'taskDefinition.taskDefinitionArn' --output text",
        returnStdout: true
    ).trim()
    def serviceExistsOutput = sh(
    script: "aws ecs describe-services --cluster $ECS_CLUSTER_NAME --services $ECS_SERVICE_NAME --region ${AWS_REGION} --no-cli-pager --output json",
    returnStdout: true
        )

    def serviceExists = new groovy.json.JsonSlurper().parseText(serviceExistsOutput)
    // def serviceExists = sh(script: "aws ecs describe-services --cluster $ECS_CLUSTER_NAME --services $ECS_SERVICE_NAME --region ${AWS_REGION}",returnStdout: true)
        echo "Returning ${serviceExists.services.status}"
    
    // if (serviceExists != 0) {

    //     sh "aws ecs update-service --cluster $ECS_CLUSTER_NAME --service $ECS_SERVICE_NAME  --region ${AWS_REGION} --desired-count 1 --force-new-deployment"
    // } else {

        // sh "aws ecs create-service --cluster $ECS_CLUSTER_NAME --service-name $ECS_SERVICE_NAME --task-definition '${taskDefinitionArn}' --desired-count '1' --launch-type 'FARGATE' --network-configuration 'awsvpcConfiguration={subnets=[subnet-064d3272b4081aa26,subnet-026a4de3b32ce30d4],securityGroups=[sg-02a405b8bb4fd7b4f],assignPublicIp=ENABLED}' --enable-execute-command --region ${AWS_REGION}"
    // }

}
