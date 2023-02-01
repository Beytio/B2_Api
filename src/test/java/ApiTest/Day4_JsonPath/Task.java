package ApiTest.Day4_JsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

 /*

    TASK
    Given accept type json
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    Verify the first id is 1
    Verify the first name is aFm
    Verify the last id is 102
    Verify the last name is GHAN
     */

    @Test
    public void testALlUserWithPath(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().log().all()
                .get("/allusers/alluser");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");


        int id=response.path("id[0]");
        Assert.assertEquals(id,1);

        String name=response.path("name[0]");
        Assert.assertEquals(name,"aFm");


        int lastID=response.path("id[-1]");
        Assert.assertEquals(lastID,102);

        String lastname=response.path("name[-1]");
        Assert.assertEquals(lastname,"GHAN");

    }




}
