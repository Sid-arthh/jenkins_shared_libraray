def call(String imageUri,String AppName,String Port,String familyName) {
    def taskDefinition = """
    {
    "family": "'${familyName}'",
    "networkMode": "awsvpc",
    "taskRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "executionRoleArn": "arn:aws:iam::491396807599:role/ecsTaskExecutionRole",
    "requiresCompatibilities": [
        "FARGATE"
    ],
    "cpu": "1024",
    "memory": "3072",
    "runtimePlatform": {
        "cpuArchitecture": "X86_64",
        "operatingSystemFamily": "LINUX"
    },
    "containerDefinitions": [
        {
            "name": "nginx-container",
            "image": "'${imageUri}'",
            "cpu": 1024,
            "essential": true,
            "portMappings": [
                {
                    "name": "'${AppName}'",
                    "containerPort": "'${PORT}'",
                    "hostPort": "'${PORT}'",
                    "protocol": "tcp"
                }
            ]
        }
    ]
}
    """
    return taskDefinition
}
