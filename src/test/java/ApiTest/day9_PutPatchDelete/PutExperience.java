package ApiTest.day9_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class PutExperience {


    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void updateExperience(){

        String body="{\n" +
                "  \"job\": \"QA\",\n" +
                "  \"company\": \"Amazon\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2022-07-28\",\n" +
                "  \"todate\": \"2023-01-25\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"experience updated\"\n" +
                "}";


        Response response= RestAssured.given().accept(ContentType.JSON)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAyIiwic3RhcnQiOjE2NzQ4Mzg5NDEsImVuZHMiOjE2NzU0NDM3NDF9.Z8jhJ-n0PJhxdM29AO0gJL-yb_ArW7hGgkbuJY10xDA2QWfDOn9W7ViW8iEGWQXhaLZ0CpkeAdne0fCPhH2u7w")
                .queryParam("id",240)
                .body(body)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();

    }
}
