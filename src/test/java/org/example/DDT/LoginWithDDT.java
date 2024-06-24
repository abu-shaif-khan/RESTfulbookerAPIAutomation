package org.example.DDT;

import org.example.utils.UtilExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginWithDDT {

    @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
    public void testLoginWithDDT(String UserName, String Password) {
        System.out.println("userName: " + UserName);
        System.out.println("password: " + Password);



    }
}
