package org.example.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endpoints.APIConstants;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

// BaseTest use for only common functionality on the testing

public class BaseTest {

    //  Before Running a Method -
    //  Rest Assured . given with the Base URI. PATH
    public static RequestSpecification requestSpecification;
    public static PayloadManager payloadManager;
    public static Response response;
    public static JsonPath jsonPath;
    public static ValidatableResponse validatableResponse;
    public static AssertActions assertActions;



    @BeforeMethod(alwaysRun = true)
    public void setConfig() {
        // Reset the Rest Assured Base URLs
        // Base URL
        // Content Type - ALL

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON);

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();

    }

    public String getToken() throws JsonProcessingException {

        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = payloadManager.setToken();
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .when().post();
        // fetch the data
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");
    }

}