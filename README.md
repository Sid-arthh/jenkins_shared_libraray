# Jenkins Shared Library for Deploying and Updating ECS App [FARGATE]

This repository contains a Jenkins shared library for deploying and updating an application on Amazon Elastic Container Service (ECS). This shared library simplifies the deployment process by providing reusable Jenkins pipeline steps and functions.

## Repository Structure

### Resources Directory

- jenkins_pipeline: The main Jenkins pipeline script that utilizes the shared library.

### Vars Directory
#### 1. `ecsUtils.groovy`

##### The ecsUtils.groovy file defines an ECS task definition JSON in Groovy code. This task definition is used to configure how your Docker container should run within an ECS service or task when using AWS Fargate. Let's break down the key components of this task definition:
    Family Name: springboot
        This sets a name to group different revisions of the same task definition.

    Network Mode: awsvpc
        Specifies the networking mode for the task, tailored for Fargate.

    Task and Execution Roles: IAM roles (ecsTaskExecutionRole) that grant permissions to the task.

    Compatibility: Supports only Fargate as the execution environment.

    Resource Allocation: Allocates 1 vCPU and 3 GB of memory to the task.

    Runtime Platform: Specifies details like CPU architecture and OS family.

    Container Definition:
        Name: nginx-container
        Docker Image: The image URL is passed as a parameter (imageUri).
        CPU Allocation: 1024 units.
        Essential: Indicates that this container is essential for the task.
        Port Mapping: Maps container port 8090 to host port 8090 using TCP protocol.

This script allows you to dynamically generate a task definition based on the Docker image URI you provide. This task definition is crucial for running your application on AWS Fargate within an ECS service or task.

#### 2. `appBuilder.groovy`

This file contains functions for building and Dockerizing your application.

- buildApp(): Builds the application using Maven.
- DockerBuildPushToECR(): Builds a Docker image and pushes it to Amazon Elastic Container Registry (ECR).

#### 3. `deployToECS.groovy`

This file contains a function for deploying the application to ECS.

- call(String imageUri): Deploys the application to ECS based on the provided Docker image URI.



## Jenkins Pipeline

To use this Jenkins shared library in your Jenkins pipeline, you can include the following code in your Jenkinsfile:

```groovy
@Library("my-shared-lib") _

pipeline {
    agent any
    environment {
        AWS_REGION = "us-east-1"
        ECR_REPO_NAME = "update"
        ECS_CLUSTER_NAME = 'SHARED_LIB'
        ECS_SERVICE_NAME = 'SERVICE5'
    }
    stages {
        // Define your stages here
    }
}
```

Make sure you have Jenkins configured with the shared library loaded from this repository.
Create or update your Jenkins pipeline using the provided Jenkinsfile.
Customize the environment variables and pipeline stages according to your application and deployment needs.
Run the Jenkins pipeline to deploy and update your ECS application.

License

This project is licensed under the TTN License.

Feel free to contribute, report issues, or suggest improvements to help make this shared library even better

