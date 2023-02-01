package ApiTest.day8_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class POST_Education {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void postNewUser(){

        NewUserInfo newUserInfo=new NewUserInfo("elif5","elif5@krafttechexlab.com",
                "elif5","About me5","5");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)//serialization
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token=response.path("token");
        String educationBody="{\n" +
                "    \"school\": \"krafttech\",\n" +
                "    \"degree\": \"JAva\",\n" +
                "    \"study\": \"QA\",\n" +
                "    \"description\": \"QA Bootcamp\",\n" +
                "    \"fromdate\": \"2022-10-19\",\n" +
                "    \"todate\": \"2023-01-29\"\n" +
                "}";

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .post("/education/add");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

    }


    @Test
    public void postNewUserAndVerify(){

        String name="elif10";
        String email="elif10@krafttechelab.com";
        String password="elif10";
        String about="About me";
        String terms="10";

        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("name",name);
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("about",about);
        requestMap.put("terms",terms);

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)//serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token=response.path("token");


        Map<String ,Object> educationBody=new HashMap<>();

        educationBody.put("school","krafttech");
        educationBody.put("degree","JAva");
        educationBody.put("study","QA");
        educationBody.put("description","QA Bootcamp");
        educationBody.put("fromdate","2022-10-19");
        educationBody.put("todate","2023-01-29");


        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .post("/education/add");

        //verify body
        String id=response.path("id");

        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .pathParam("id",id)
                .when()
                .get("education/getbyid/{id}");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

        //verify with path
//        assertEquals(response.path("userid"), userId );
        assertEquals(response.path("school"), "krafttech" );
        assertEquals(response.path("study"), "QA" );

        //verify using hamcrestmatcher

        given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .pathParam("id",id)
                .when()
                .get("education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school", equalTo("krafttech"),
                        "study",equalTo("QA")).log().all();







    }


}
