package ApiTest.day5_mentor_API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class getRequestWithParam {
    String kraftURL="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void getReuest_PathParam(){

        //ilk yol
        Response response = RestAssured
                .when()
                .get(kraftURL + "/allusers/getbyid/1");

//        New Way
        Response response1 = RestAssured
                .given().accept(ContentType.JSON)
                .pathParam("id",1)
                .when()
                .get(kraftURL + "/allusers/getbyid/{id}");

        Assert.assertEquals(response1.statusCode(),200);
        Assert.assertTrue(response1.body().asString().contains("afmercan@gmail.com"));

    }

    @Test
    public void getRequestQueryParam(){
//        /allusers/alluser

        Response response = RestAssured
                .given()
                .queryParam("pagesize", 15)
                .queryParam("page", 1)
                .when()
                .get(kraftURL + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));


    }

    @Test
    public void getRequestQueryParamMap(){
//        query paramları map e atarak yaparsak .queryParams methodu kullanıyoruz
    Map<String,Object> map=new HashMap<>();
    map.put("pagesize",10);
    map.put("page",1);

        Response response = RestAssured
                .given()
//                .queryParam("pagesize", 15)
//                .queryParam("page", 1)
                .queryParams(map)
                .when()
                .get(kraftURL + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));


    }

}
