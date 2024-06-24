package org.example.utils;

import com.github.javafaker.Faker;

public class FakerUtil {


    public static String getUserName(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }
}
