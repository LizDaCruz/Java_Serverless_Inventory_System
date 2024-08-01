# Java_Serverless_Inventory_System
A custom serverless inventory management system using Java, AWS Lambda, AWS SAM, Amazon Cognito, AWS API Gateway, and DynamoDB


# Set Up Your Environment
Install AWS CLI: Install and configure to connect to your AWS environment.

Install AWS SAM CLI: Configure for deploying and managing the application.

Install Java JDK: Language this is being built in.

Install Maven: For building Java applications.



# Setup Steps in Bash

## Create project directory

mkdir inventory-management

cd inventory-management

## Initalize AWS SAM

sam init --runtime java11 --name inventory-management

cd inventory-management


# Test Inventory System

Add Product

HTTP Method: POST

Endpoint: /add-product/{productId}

Query Parameters: productName, price


curl -X POST "https://<api-id>.execute-api.<region>.amazonaws.com/Prod/add-product/12345?productName=SampleProduct&price=19.99"


Update Product

HTTP Method: PUT

Endpoint: /update-product/{productId}

Query Parameters: productName, price


curl -X PUT "https://<api-id>.execute-api.<region>.amazonaws.com/Prod/update-product/12345?productName=UpdatedProduct&price=29.99"


Remove Product

HTTP Method: DELETE

Endpoint: /remove-product/{productId}


curl -X DELETE "https://<api-id>.execute-api.<region>.amazonaws.com/Prod/remove-product/12345"

# Using Postman

Select the HTTP method (POST, PUT, DELETE).

Enter the URL (e.g., https://<api-id>.execute-api.<region>.amazonaws.com/Prod/add-product/12345).

Add query parameters (for POST and PUT methods) and send the request.

# Responses

Success Response: 200 OK with a success message.

Error Response: Returns an appropriate error status code (e.g., 500 Internal Server Error) with an error message.
