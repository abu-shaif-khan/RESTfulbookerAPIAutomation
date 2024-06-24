package org.example.endpoints;

import org.example.utils.FilloUtil;
import org.example.utils.PropertyReaderUtil;

public class APIConstants {


    public static String BASE_URL;

    static {
        try {
//            BASE_URL = PropertyReaderUtil.readKey("url");
             BASE_URL = FilloUtil.fetchDataFromXLSX("Sheet1", "baseUrl", "Value");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static String BASE_URL = "https://restful-booker.herokuapp.com";

    public static String CREATE_BOOKING = "/booking";
    public static String UPDATE_BOOKING = "/booking";
    public static String DELETE_BOOKING = "/booking";

}
