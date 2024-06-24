package org.example.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.payloads.request.Auth;
import org.example.payloads.request.Booking;
import org.example.payloads.request.Bookingdates;
import org.example.payloads.response.BookingRespons;
import org.example.utils.FakerUtil;

public class PayloadManager {
//    java -> json to that when give it to th .body()?
//    jr qa - all the payload we will keep it here


    public String createPayload_Positve() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

//        serializer for input Stream
        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getUserName());
        booking.setLastname("Khan");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }

// Deserializer when we have to verify all the json object
    public BookingRespons JsonToObject (String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BookingRespons bookingRespons = objectMapper.readValue(jsonString, BookingRespons.class);
        return bookingRespons;
    }

    // Deserializer
    public Booking JsonToObjectPUT (String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Booking booking = objectMapper.readValue(jsonString, Booking.class);
        return booking;
    }

    public String createPayload_negative() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

//        serializer for input Stream
        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getUserName());
        booking.setLastname("Khan");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;

    }

    // set token the serializer
    public String setToken() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);

    }

    public String updatedBooking() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getUserName());
        booking.setLastname("Dutta");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }
}
