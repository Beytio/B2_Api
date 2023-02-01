package ApiTest.day6_HemcrestMatcher;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;

public class HemcretsMatcher {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    /*
        given accept type is json
        And path param id is 111
        When user sends a get request to /allusers/getbyid/{id}
        Then status code should be 200
        And content type should be application/json; charset=UTF-8
        And json data has following:
         "id"= 111
         "name"= "Thomas Eduson"
         "job"="Developer"
         */


    @Test
    public void oneUserWith_HamCrestMatchers(){

        RestAssured
                .given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8")
                .and().body("id[0]",equalTo(111),"name[0]",equalTo("Thomas Eduson"),
                        "job[0]",equalTo("Developer"));


        // her nekadar sadece 1 adet veride olsa içinde yukarıdaki ifade array içerisene attğı için veriler
//        mutlaka id[0] şeklinde çağırımlaıdır
    }





/*
        given accept type is json
        And query param pagesize is 50
        And query param page is 1
        When user sends a get request to /allusers/alluser
        Then status code should be 200
        And content type should be application/json; charset=UTF-8
        And response header content-length should be 8653
        And response header server should be Apache/2
        And response headers has Date
        And json data should have "GHAN","aFm","Sebastian"
        And json data should have "QA" for job
        And json data should have "Junior Developer1" for first user's experience job
         */

    @Test
    public void allUserWith_HamCrestMatchers() {

        RestAssured
                .given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8")
                .and()
                .assertThat().header("content-length", equalTo("8653"))
                .header("server", equalTo("Apache/2"))
                .header("Date", notNullValue())
                .body("name", hasItems("GHAN", "aFm", "Sebastian"),
                        "job", hasItems("QA"),
                        "experience[0].job[0]", equalTo("Junior Developer1"),
                "name[0]",equalTo("MercanS"));

    }





    }
