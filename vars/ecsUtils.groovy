def call(String imageUri) {
    def taskDefinition = """
    {
    "family": "nginx-task",
    "networkMode": "awsvpc",
    "requiresCompatibilities": ["FARGATE"],
    "executionRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "containerDefinitions": [
        {
            "name": "nginx-container",
            "image": "'${imageUri}'",
            "memory": 512,
            "cpu": 256,
            "essential": true,
            "portMappings": [
                {
                    "containerPort": 80,
                    "hostPort": 80
                }
            ]
        }
    ]
}
    """
    return taskDefinition
}
