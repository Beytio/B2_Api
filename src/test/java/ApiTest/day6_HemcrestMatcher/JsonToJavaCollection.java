package ApiTest.day6_HemcrestMatcher;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }



    @Test
    public void UserToMap(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11");

        Assert.assertEquals(response.statusCode(),401);
        Map<String ,Object> jsonMap=response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        //verify the message

        String message= (String) jsonMap.get("message");
        System.out.println("message = " + message);
        Assert.assertEquals(message,"User not authorized!");

        //verify code
        String code= (String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code,"1200");

    }


    @Test
    public void allUserMap(){
        Response response=RestAssured.given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        //we need to deserialiaze Json response to Java collection
        // birden fazla body bulunduğu için list of map yapmamız gerekiyor
        List<Map<String ,Object>> allUserList=response.body().as(List.class);
        System.out.println("allUserList = " + allUserList);

        // 2. kullanıcını adını assert etme
        System.out.println("allUserList.get(1).get(\"name\") = " + allUserList.get(1).get("name"));



        //1. kullaınııcının skills getir

        System.out.println("allUserList.get(0).get(\"skills\") = " + allUserList.get(0).get("skills"));

        List<String > skills= (List<String>) allUserList.get(0).get("skills");

        System.out.println("skills.get(0) = " + skills.get(0));
        Assert.assertEquals(skills.get(0), "PHP");


// experience list olarak getirmek
        List<Map<String,Object>> listofExperience= (List<Map<String, Object>>) allUserList.get(0).get("experience");

        System.out.println("listofExperience = " + listofExperience);

        System.out.println("listofExperience.get(1).get(\"job\") = " + listofExperience.get(0).get("location"));


    }

}
