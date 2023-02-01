package ApiTest.Day4_JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.JsonbMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class UserWith_JsonPath {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

/*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And user's name should be Thomas Eduson
    And user's id should be 111
    And user's email should be thomas@test.com
   */


    @Test
    public void task1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        response.prettyPrint();

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);

        //verify content type
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        int idJson=jsonPath.getInt("id[0]");
        String nameJson=jsonPath.getString("name[0]");
        String emailJson=jsonPath.getString("email[0]");


        Assert.assertEquals(idJson,111);
        Assert.assertEquals(nameJson,"Thomas Eduson");
        Assert.assertEquals(emailJson,"thomas@test.com");


    }


    /*
    TASK
    Given accept type is json
    When user sends a GET request to /allusers/alluser
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And second name should be isa akyuz
    And first user's experience jobs should be Junior Developer1, Junior Developer1, Junior Developer
     */

    @Test
    public void task2() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser");

//        response.prettyPrint();

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);

        //verify content type
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        String secondNameJson=jsonPath.getString("name[1]");
        System.out.println("secondNameJson = " + secondNameJson);

        Assert.assertEquals(secondNameJson,"isa akyuz");

        List<String > jobs=jsonPath.getList("experience.job[0]");   //    gpath syntex
        System.out.println("jobs = " + jobs);

        Assert.assertEquals(jobs, "Junior Developer1, Junior Developer1, Junior Developer");


    }



    /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    Get user skills
    And user's first skill should be PHP

   */
    @Test
    public void task3() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");

//        response.prettyPrint();

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);

        //verify content type
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

////        1.yol
//
//        String skillss=jsonPath.getString("skills");
//        System.out.println("skillss = " + skillss);
//        String firstSkills= jsonPath.getString("skills[0][0]");



//         2.yol
        List<String > skills=jsonPath.getList("skills[0]");
        System.out.println("skills = " + skills);

        String firstSkill = skills.get(0);
        Assert.assertEquals(firstSkill,"PHP");

    }


    }
