def call(String imageUri) {
    def taskDefinition = """
    {
    "family": "nginxx",
    "networkMode": "awsvpc",
    "requiresCompatibilities": ["FARGATE"],
    "executionRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "taskRoleArn": "arn:aws:iam::491396807599:role/ecsTaskRole",
    "cpu": "256",
    "memory": "512",
    "containerDefinitions": [
        {
            "name": "nginx-container",
            "image": "'${imageUri}'",
            "memory": 512,
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
