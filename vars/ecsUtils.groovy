def call(String imageUri) {
    def taskDefinition = """
    {
    "family": "nginx-task",
    "networkMode": "awsvpc",
    "requiresCompatibilities": ["FARGATE"],
    "executionRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "cpu": "256",
    "memory": "512",
    "desiredCount": "1",
    "containerDefinitions": [
        {
            "name": "nginx-container",
            "image": "public.ecr.aws/nginx/nginx:mainline-alpine",
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
