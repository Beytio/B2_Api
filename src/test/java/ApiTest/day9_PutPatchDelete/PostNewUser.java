package ApiTest.day9_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class PostNewUser {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void NewUser(){
        String body="{\n" +
                "  \"name\": \"elif11\",\n" +
                "  \"email\": \"elif11@krafttechexlab.com\",\n" +
                "  \"password\": \"elif11235\",\n" +
                "  \"about\": \"From Ankara11\",\n" +
                "  \"terms\": \"11\"\n" +
                "}";

        Response response= RestAssured.given().accept(ContentType.JSON)
                .body(body)
                .when().log().all()
                .post("/allusers/register").prettyPeek();

        String token=response.path("token");
        System.out.println("token = " + token);

    }

    //  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAyIiwic3RhcnQiOjE2NzQ4Mzg5NDEsImVuZHMiOjE2NzU0NDM3NDF9.Z8jhJ-n0PJhxdM29AO0gJL-yb_ArW7hGgkbuJY10xDA2QWfDOn9W7ViW8iEGWQXhaLZ0CpkeAdne0fCPhH2u7w



}
