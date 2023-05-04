
# PetMinder

Registration requires email verification. By default, the application will start up with one user named `admin` with the password `petminder`.

## Requirements

- Java 17 or higher
- Maven

## Getting Started Locally

1. Clone the repository:

2. Change to the project directory:

3. Build the project using Maven:
   mvn clean install -DskipTests
   mvn liquibase:clearCheckSums
   mvn liquibase:update

4. run the application
   mvn spring-boot:run

The application will be available at `http://localhost:8080`.

## Getting Started in docker
Suitable if only wokring with UI, or just for a quick run, if using constantly it can be slow because each run will need to download all mvn packages

1. Clone the repository:

2. Change to the project directory:

3. Build the project using docker:
   docker build -t petminder-api .
   docker run --name petminder-container -p 8080:8080 petminder-api


## Login and Registration

The application has the following API endpoints for login and registration:

- `/api/login`: Authenticate a user with their email and password.
- `/api/register`: Register a new user by providing their email, password, and other required information. Registration requires email verification.

## Email Verification

When a user registers, they will receive a verification email with a confirmation link. They must click the link to verify their email address before they can log in.

## Default Admin User

By default, the application starts with one user named `admin` with the password `petminder`. You can use this account to access the application and perform administrative tasks.

