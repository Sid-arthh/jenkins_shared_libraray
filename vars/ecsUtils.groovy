def call(String imageUri) {
    def taskDefinition = """
    {
    "family": "nginx-task",
    "networkMode": "awsvpc",
    "requiresCompatibilities": ["FARGATE"],
    "executionRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "cpu": "256",  // Define the cpu value at the task level
    "memory": "512", // Define the memory value at the task level
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
