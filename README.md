# Jenkins Shared Library for Deploying and Updating ECS App

This repository contains a Jenkins shared library for deploying and updating an application on Amazon Elastic Container Service (ECS). This shared library simplifies the deployment process by providing reusable Jenkins pipeline steps and functions.

## Repository Structure

### Resources Directory

- `jenkins_pipeline`: The main Jenkins pipeline script that utilizes the shared library.

### Vars Directory

#### 1. `appBuilder.groovy`

This file contains functions for building and Dockerizing your application.

- `buildApp()`: Builds the application using Maven.
- `DockerBuildPushToECR()`: Builds a Docker image and pushes it to Amazon Elastic Container Registry (ECR).

#### 2. `deployToECS.groovy`

This file contains a function for deploying the application to ECS.

- `call(String imageUri)`: Deploys the application to ECS based on the provided Docker image URI.

#### 3. `ecsUtils.groovy`

This file contains a function for defining the ECS task definition.

- `call(String imageUri)`: Generates an ECS task definition JSON based on the provided Docker image URI.

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

