package ApiTest.day8_POST_Request;

import ApiTest.day7_Deserialization.PetStoreUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){

        String jsonBody="{\n" +
                "  \"name\": \"elif1\",\n" +
                "  \"email\": \"elif1@krafttechexlab.com\",\n" +
                "  \"password\": \"elif1123\",\n" +
                "  \"about\": \"From Ankara1\",\n" +
                "  \"terms\": \"21\"\n" +
                "}";

        Response response= RestAssured.given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }


    @Test
    public void postNewUserMap(){

        Map<String ,Object> requestMap=new HashMap<>();

        requestMap.put("name","elif2");
        requestMap.put("email","elif2@krafttechexlab.com");
        requestMap.put("password","elif2222");
        requestMap.put("about","From Ank");
        requestMap.put("terms","1");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)//serialization
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUserPOJO(){
//        PetStoreUser oneUser=response.body().as(PetStoreUser.class);

        NewUserInfo newUserInfo=new NewUserInfo();

        newUserInfo.setName("elif3");
        newUserInfo.setEmail("elif3@krafttechexlab.com");
        newUserInfo.setPassword("elif3");
        newUserInfo.setAbout("from ank3");
        newUserInfo.setTerms("1");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)//serialization
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }


    @Test
    public void postNewUser4(){
        NewUserInfo newUserInfo=new NewUserInfo("elif4","elif4@krafttechexlab.com",
                "elif4","About me","1");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)//serialization
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }


}
