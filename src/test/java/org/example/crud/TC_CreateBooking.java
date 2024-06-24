package org.example.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.example.base.BaseTest;
import org.example.endpoints.APIConstants;
import org.example.payloads.response.BookingRespons;
import org.example.utils.PropertyReaderUtil;
import org.example.utils.YAMLReader;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;




public class TC_CreateBooking extends BaseTest {
    @Test(groups = {"stage", "p0"})
    @Owner("Shaif")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that create Booking works and ID")
    public void testCreateBooking_TC_positive() throws JsonProcessingException {

//        call the request block
        //  call the rest assured, Given baseurl(change), payload(change)
//        call the payload
//        call the assertion block

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload_Positve()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
//        we are using jsonpath for only getting some information from response body from json
//        jsonPath = JsonPath.from(response.asString());
//        String bookingId = jsonPath.get("bookingid");
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        assertThat(bookingRespons.getBookingid().toString()).isNotEmpty().isNotNull();

    }

    @Test(groups = {"stage", "p0"})
    public void testCreateBooking_TC_negative() throws JsonProcessingException {

//        call the request block
        //  call the rest assured, Given baseurl(change), payload(change)
//        call the payload
//        call the assertion block

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body(payloadManager.createPayload_negative()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
//        we are using jsonpath for only getting some informationa from response body from json
//        jsonPath = JsonPath.from(response.asString());
//        String bookingId = jsonPath.get("bookingid");
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        assertThat(bookingRespons.getBookingid().toString()).isNotEmpty().isNotNull();

    }

    @Test(groups = {"stage", "p0"})
    public void testCreateBooking_TC_negative_without_payload() throws JsonProcessingException {

//        call the request block
        //  call the rest assured, Given baseurl(change), payload(change)
//        call the payload
//        call the assertion block

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body("").post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
//        new YAMLReader().readKey().get("username");
//        new PropertyReaderUtil().readKey().get("username");
//        new ExcelReader().readKey().get("username");
//        new JSONReader().readKey().get("username");
//        new TextReader().readKey().get("username");
//        new ENVReader().readKey().get("username");
//        new XMLReader().readKey().get("username");

    }
}


