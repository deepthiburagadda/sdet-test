package com.sdet.test.invokers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class NameServiceInvokerTest {

    NameServiceInvoker invoker;

    @BeforeTest
    public void invokeService() {
        invoker = new NameServiceInvoker();
    }

    @Test
    @Parameters({"ValidName","CapitalName"})
    public void validName(String validName,String capitalName){
       Response res =  invoker.getServiceResponse(validName);
        JsonPath jsonPath = res.jsonPath();
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(200,res.getStatusCode());
        sa.assertEquals(validName,jsonPath.getString("name[0]"));
        sa.assertEquals(capitalName,jsonPath.getString("capital[0]"));

    }

    @Test
    @Parameters({"Name"})
    public void checkResultsSize(String name){
        Response res =  invoker.getServiceResponse(name);
        JsonPath jsonPath = res.jsonPath();
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(200,res.getStatusCode());
        List<String> results = jsonPath.getList("$");
        sa.assertEquals("35",results.size());

    }

    @Test
    @Parameters({"InValidName"})
    public void invalidName(String invalidName){
        Response res =  invoker.getServiceResponse(invalidName);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(404,res.getStatusCode());
    }

    @Test
    @Parameters({"EmptyName"})
    public void emptyName(String emptyName){
        Response res =  invoker.getServiceResponse(emptyName);
        SoftAssert sa= new SoftAssert();
        sa.assertEquals(404,res.getStatusCode());
    }

}
