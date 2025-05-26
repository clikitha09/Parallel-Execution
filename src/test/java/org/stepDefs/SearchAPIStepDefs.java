package org.stepDefs;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class SearchAPIStepDefs {
    Response response;
    String keyword;

    @Given("I search for a product in api using {string}")
    public void i_search_for_product(String keyword) {
        System.out.println("Thread " + Thread.currentThread().getId() + " - Searching for: " + keyword);
        this.keyword = keyword;
        response = given()
                .baseUri("https://dummyjson.com")
                .queryParam("q", keyword)
                .when()
                .get("/products/search");
    }

    @Then("I should receive a valid response of api with products related to {string}")
    public void validate_response(String keyword) {
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Parse response body
        JSONObject json = new JSONObject(response.getBody().asString());
        JSONArray products = json.getJSONArray("products");

        // Validate products list is not empty
        Assert.assertTrue(products.length() > 0, "No products returned");

        // Check that at least one product title contains the keyword (case-insensitive)
        boolean foundMatch = false;
        for (int i = 0; i < products.length(); i++) {
            String title = products.getJSONObject(i).getString("title");
            if (title.toLowerCase().contains(keyword.toLowerCase())) {
                foundMatch = true;
                break;
            }
        }

        Assert.assertTrue(foundMatch, "No product title contains the keyword: " + keyword);
    }
}

