package com.sdet.test;


import com.sdet.test.invokers.CodeServiceInvoker;
import com.sdet.test.invokers.NameServiceInvoker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.*;

public class SearchByNameOrCode {
    public static void main(String[] args) {
        String str;
        Scanner in = new Scanner(System.in);
        System.out.println("Please type EXIT to quit");
        while (true) {
            System.out.println("Enter a string: ");
            str = in.nextLine();
            if(null == str || str.length() == 0){
                System.out.println("Please enter valid input");
                continue;
            }
            if("exit".equalsIgnoreCase(str)){
                break;
            }
            Map<String, String> results = getCapitalByNameOrCode(str);
            Set<Map.Entry<String, String>> entrySet=  results.entrySet();
            Iterator<Map.Entry<String, String>> it = entrySet.iterator();
            System.out.println("COUNTRY NAME" + "::" + " CAPITAL");
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                System.out.println(entry.getKey() + " ::  " + entry.getValue());
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }

    public static Map<String, String> getCapitalByNameOrCode(String input){
        Response codeResponse = null;
        if(input.length() == 2 || input.length() == 3){
            codeResponse = new CodeServiceInvoker().getServiceResponse(input);
        }
        Response nameResponse = new NameServiceInvoker().getServiceResponse(input);
        return extractCountryNameAndCapital(codeResponse, nameResponse);
    }

    public static Map<String, String> extractCountryNameAndCapital(Response codeResponse, Response nameResponse){
        Map<String, String> results = new HashMap<String, String>();
        JsonPath jsonPathValidatorName = nameResponse.jsonPath();
        if(nameResponse.statusCode() == 200){
            List <String> jsonResponseRoot = jsonPathValidatorName.getList("$");
            for(int i = 0; i < jsonResponseRoot.size(); i++){
                results.put(jsonPathValidatorName.getString("name["+i+"]"), jsonPathValidatorName.getString("capital["+i+"]") );
            }
        }
        if(null != codeResponse && nameResponse.statusCode() == 200){
            JsonPath jsonPathValidatorCode = codeResponse.jsonPath();
            results.put(jsonPathValidatorCode.getString("name"), jsonPathValidatorCode.getString("capital"));
        }
        return results;
    }
}
