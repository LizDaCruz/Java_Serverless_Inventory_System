package com.company.inventory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;

public class AddProductHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DynamoDB dynamoDB;
    private final String tableName = "ProductTable";

    public AddProductHandler() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        this.dynamoDB = new DynamoDB(client);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String productId = request.getPathParameters().get("productId");
        String productName = request.getQueryStringParameters().get("productName");
        String price = request.getQueryStringParameters().get("price");

        Table table = dynamoDB.getTable(tableName);
        Item item = new Item()
            .withPrimaryKey("productId", productId)
            .withString("productName", productName)
            .withNumber("price", Double.parseDouble(price));

        try {
            table.putItem(item);
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Error adding product");
        }

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Product added successfully.");
    }
}
