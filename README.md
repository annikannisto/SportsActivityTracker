# Sports Activity Tracker

## Objective
This application is designed to help track the time spent on sports activities during the week. It also demonstrates the use of version control with GitHub, continuous integration and deployment using Jenkins, containerization with Docker, and running the application in an online Docker environment using Docker Hub Lab.

## Features
- Log various sports activities with a description and duration.
- View all logged activities.
- Calculate the total time spent on activities for the week.
- Input validation to handle invalid inputs gracefully.

## Prerequisites
- **Java Development Kit (JDK) 17**: Ensure JDK 17 is installed.
- **Maven**: For building the project.
- **Docker**: For creating and running the Docker image.
- **Jenkins**: For setting up the CI/CD pipeline.

## Setup Instructions

### 1. Version Control with GitHub
1. Initialize a Git repository in your project directory:
    ```bash
    git init
    ```
2. Add your files and make frequent commits with meaningful messages:
    ```bash
    git add .
    git commit -m "Initial commit - Sports Activity Tracker application"
    ```
3. Push the repository to your GitHub account:
    ```bash
    git remote add origin "https://github.com/your-username/SportsActivityTracker.git"
    git push -u origin main
    ```
4. Ensure the repository contains a README file explaining the project and how to use the application.

### 2. Build the Project with Maven
Compile the code and run tests:
```bash
mvn clean install
```

### 3. Testing
To run the tests, use:
```bash
mvn test
```

### 4. Continuous Integration with Jenkins
1. **Set up Jenkins**: Install Jenkins on your system or use a cloud-based instance.
2. **Create a Pipeline**: In Jenkins, create a new pipeline job and use the following stages:
    - **Checkout**: Pull the latest code from your GitHub repository.
    - **Build**: Use Maven to compile and package the application.
    - **Test**: Run unit tests using Maven.
    - **Code Coverage**: Generate a code coverage report using JaCoCo.
    - **Docker Build**: Build a Docker image using the Dockerfile in the project.
    - **Docker Push**: Push the Docker image to Docker Hub.

3. **Sample Pipeline Script**:
    ```groovy
    pipeline {
        agent any
        stages {
            stage('Checkout') {
                steps {
                    git url: 'https://github.com/your-username/SportsActivityTracker.git', branch: 'main'
                }
            }
            stage('Build') {
                steps {
                    sh 'mvn clean package'
                }
            }
            stage('Test') {
                steps {
                    sh 'mvn test'
                }
            }
            stage('Code Coverage') {
                steps {
                    sh 'mvn jacoco:report'
                }
            }
            stage('Docker Build and Push') {
                steps {
                    script {
                        withCredentials([string(credentialsId: 'docker-hub-credentials', variable: 'DOCKER_HUB_PASSWORD')]) {
                            sh 'docker login -u your-dockerhub-username -p $DOCKER_HUB_PASSWORD'
                            sh 'docker build -t your-dockerhub-username/sportsactivitytracker .'
                            sh 'docker push your-dockerhub-username/sportsactivitytracker'
                        }
                    }
                }
            }
        }
        post {
            always {
                cleanWs() // Clean up workspace
            }
        }
    }
    ```

### 5. Containerization with Docker
1. **Create a Dockerfile**: Add the following `Dockerfile` to your project directory:
    ```dockerfile
    # Use Maven to build the application
    FROM maven:3.8.4-openjdk-17 AS build
    WORKDIR /app
    COPY pom.xml /app/
    COPY . /app/
    RUN mvn clean package

    # Use OpenJDK to run the application
    FROM openjdk:17-jdk-slim
    WORKDIR /app
    COPY --from=build /app/target/SportsActivityTracker.jar /app/Test.jar
    CMD ["java", "-jar", "Test.jar"]
    ```
2. **Build the Docker Image**:
    ```bash
    docker build -t your-dockerhub-username/sportsactivitytracker .
    ```
3. **Push the Docker Image to Docker Hub**:
    ```bash
    docker push your-dockerhub-username/sportsactivitytracker
    ```

### 6. Running the Application in Docker Hub Lab
1. Go to [Play with Docker](https://labs.play-with-docker.com/) and sign in.
2. Click "Start" to launch a new Docker environment.
3. Pull your Docker image:
    ```bash
    docker pull your-dockerhub-username/sportsactivitytracker
    ```
4. Run the Docker container:
    ```bash
    docker run -it --rm your-dockerhub-username/sportsactivitytracker
    ```

## Author
- Anni - (https://github.com/annikannisto)
