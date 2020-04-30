package com.sdet.test.invokers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CodeServiceInvokerTest {

    CodeServiceInvoker invoker;

    @BeforeTest
    public void invokeService() {
        invoker = new CodeServiceInvoker();
    }

    @Test
    @Parameters({"ValidCode","ValidThreeLetterCode","Capital"})
    public void validCode(String validCode,String threeLetterValidCode,String capital){
       Response res =  invoker.getServiceResponse(validCode);
        JsonPath jsonPath = res.jsonPath();
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(200,res.getStatusCode());
        sa.assertEquals(validCode,jsonPath.getString("alpha2Code[0]"));
        sa.assertEquals(threeLetterValidCode,jsonPath.getString("alpha3Code[0]"));
        sa.assertEquals(capital,jsonPath.getString("capital[0]"));

    }

    @Test
    @Parameters({"InValidCode"})
    public void invalidCode(String invalidCode){
        Response res =  invoker.getServiceResponse(invalidCode);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(404,res.getStatusCode());
    }

    @Test
    @Parameters({"InValidCodeLengthGreaterThanThree"})
    public void invalidLengthCodeGreaterThanThree(String invalidCodeLength){
        Response res =  invoker.getServiceResponse(invalidCodeLength);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(400,res.getStatusCode());
    }

    @Test
    @Parameters({"InValidCodeLengthLessThanTwo"})
    public void invalidLengthCodeLessThanTwo(String invalidCodeLength){
        Response res =  invoker.getServiceResponse(invalidCodeLength);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(400,res.getStatusCode());
    }

    @Test
    @Parameters({"EmptyCode"})
    public void emptyCode(String emptyCode){
        Response res =  invoker.getServiceResponse(emptyCode);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(404,res.getStatusCode());
    }


}
