package ApiTest.Day4_JsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class task2 {
//    @BeforeClass
//    public void beforeClass(){
//        baseURI="https://demoqa.com/BookStore/v1/Books";
//    }



    @Test
    public void task1(){

        /*
    Given accept type json
    When user sends a get request to https://demoqa.com/BookStore/v1/Books
    Then status code should be 200
    And content typr should be application/json; charset=utf-8
    And the first book isbn should be 9781449325862
    And the first book publisher should be O'Reilly Media

     */
        Response response=given().accept(ContentType.JSON)
                .when().log().all()
                .get("https://demoqa.com/BookStore/v1/Books");

      //  response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


        System.out.println("response.path(\"books.isbn[0]\") = " + response.path("books.isbn[0]"));
        Assert.assertEquals(response.path("books.isbn[0]"), "9781449325862");


        String publisher=response.path("books.publisher[0]");
        Assert.assertEquals(publisher,"O'Reilly Media");




    }
}
