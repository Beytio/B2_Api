package ApiTest.day10_mentör;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class getAllUserWithJsonPath {

    @Test
    public void getAllUsers() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .queryParam("pagesize",10)
                .queryParam("page",1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/alluser");

     //   response.prettyPrint();


        //get first id
        //  1 YOL
        int id=response.path("id[0]");
        System.out.println("id = " + id);

        //2 YOL
        JsonPath jsonPath=response.jsonPath();
        int firstId=jsonPath.getInt("id[0]");
        System.out.println("firstId = " + firstId);

        // get second name
        String secondname=jsonPath.getString("name[1]");
        System.out.println("name = " + secondname);

        //get all names
        List<String > allNames=jsonPath.getList("name");
        System.out.println("name = " + allNames);

        //get all ids
        List<Object> allIds=jsonPath.getList("id");
        System.out.println("allIds = " + allIds);

        //get skills of first user

        List<String> firstUserSkills=jsonPath.getList("skills[0]");
        System.out.println("firstUserSkills = " + firstUserSkills);

        // get the first skills of first user 1 YOL
        System.out.println(jsonPath.getString("skills[0][0]"));

        // get the second skills of first user  2 YOL
        String  skillssecond = jsonPath.get("skills[0][1]");
        System.out.println("skillssecond = " + skillssecond);
        System.out.println("firstUserSkills.get(0) = " + firstUserSkills.get(0));
        System.out.println("firstUserSkills.get(1) = " + firstUserSkills.get(1));


        // first users first educations'  school

        String firstSchool=jsonPath.getString("education[0].school[0]");
        System.out.println("firstSchool = " + firstSchool);

            //2.YOL
            Map<String,Object> firstEducation=jsonPath.get("education[0][0]");
        System.out.println("firstEducation.get(\"id\") = " + firstEducation.get("id"));

    }

    }
