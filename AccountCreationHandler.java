package com.company.inventory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

public class AccountCreationHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DynamoDB dynamoDB;
    private final String tableName = "UserTable";

    public AccountCreationHandler() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        this.dynamoDB = new DynamoDB(client);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String userId = request.getPathParameters().get("userId");
        String accessLevel = request.getQueryStringParameters().get("accessLevel");

        // Store user details in DynamoDB
        Table table = dynamoDB.getTable(tableName);
        PutItemSpec spec = new PutItemSpec().withItem(new com.amazonaws.services.dynamodbv2.document.Item()
            .withPrimaryKey("userId", userId)
            .withString("accessLevel", accessLevel));

        try {
            table.putItem(spec);
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Error creating account");
        }

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Account created successfully.");
    }
}
