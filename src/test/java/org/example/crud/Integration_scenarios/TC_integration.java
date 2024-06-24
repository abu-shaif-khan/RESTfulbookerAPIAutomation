package org.example.crud.Integration_scenarios;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.payloads.request.Booking;
import org.example.payloads.response.BookingRespons;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TC_integration extends BaseTest {
    String token;
    String bookingId;
    String bookingIdPojo;
    // Get a token
    // create a booking
    // update the booking with the token and booking id
    // how to pass the variables from one test to another
        // auth - api key
        // cookie based auth side
        // Oauth 2.0
    // Delete the booking


    // get a token

//    before extract the token we need to use logger of log4j2 and change the only property name on file name
    // we use logger mostly in selenium but not so much use in api automation because Rest assure already have log
    private static final Logger log = LogManager.getLogger(TC_integration.class);

    // create a booking
    @Test(groups = {"stage", "p0"})
    public  void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload_Positve()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        // there are 2 ways to extract response body 1. json path 2. making pojo class
        // Direct Extraction from json Path - quickest way - or find some few things
        jsonPath = JsonPath.from(response.asString());
        bookingId = jsonPath.getString("bookingid");

        // only for seeing logs
        log.info("this is booking id: " + bookingId);

        // Booking Response Class - if I need to verify all the response body
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingRespons.getBookingid().toString();
        System.out.println("This is My Booking ID "+ bookingIdPojo);

        assertThat(bookingId).isNotNull().isNotEmpty();
    }

    //  update the booking with the token and booking id
    @Test(groups = {"stage", "p0"}, dependsOnMethods = {"testCreateBooking"})
    public  void testCreateAndUpdateBooking() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatedBooking()).put();
//        to give Oauth2.0 token
//        response = RestAssured.given().spec(requestSpecification).auth().oauth2("token")
//                .when().body(payloadManager.updatedBooking()).put();
        validatableResponse = response.then().log().all();
//        Default Rest assured assertions
//        validatableResponse.body("lastname", Matchers.is("Dutta"));

        // Booking Response Class - if I need to verify multiple assertions
        Booking booking = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(booking.getLastname()).isEqualTo("Dutta").isNotNull().isNotEmpty();
        assertThat(booking.getTotalprice()).isEqualTo(199).isNotNull();
        assertThat(booking.getBookingdates().getCheckin()).isEqualTo("2022-10-01").isNotNull().isNotEmpty();

    }

    // delete the booking
    @Test(groups = {"stage", "p0"}, dependsOnMethods = {"testCreateAndUpdateBooking"})
    public  void testDeleteBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();
        requestSpecification.basePath(APIConstants.DELETE_BOOKING + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token).contentType("application/json")
                .when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);


    }
}
