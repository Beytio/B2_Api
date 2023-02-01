package ApiTest.day9_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class DeleteExperience {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void deleteExperience() {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAyIiwic3RhcnQiOjE2NzQ4Mzg5NDEsImVuZHMiOjE2NzU0NDM3NDF9.Z8jhJ-n0PJhxdM29AO0gJL-yb_ArW7hGgkbuJY10xDA2QWfDOn9W7ViW8iEGWQXhaLZ0CpkeAdne0fCPhH2u7w";

        Response response= RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",240)
                .queryParam("token",token)
                .when().log().all()
                .delete("/experience/delete/{id}").prettyPeek();




    }

    public static void main(String[] args) {
        int a=3;
        int b=8;

        if(a<b)
            System.out.println("a = " + a);
        else
            System.out.println("b = " + b);


    }
}
