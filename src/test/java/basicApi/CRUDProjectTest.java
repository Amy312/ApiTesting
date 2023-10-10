package basicApi;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDProjectTest {

    @Test
    public void createProject(){
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "Amy Json");
        bodyProject.put("Icon", 3);

        Response response = given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body(bodyProject.toString())
                .log()
                .all()
        .when()
                .post("https://todo.ly/api/projects.json");
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(3));
        int idProject = response.then().extract().path("Id");

        bodyProject.put("Content", "Amy 2Json");
        response = given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body(bodyProject.toString())
                .log()
                .all()
                .when()
                .put("https://todo.ly/api/projects/"+idProject+".json");
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(3));

        response = given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body(bodyProject.toString())
                .log()
                .all()
                .when()
                .get("https://todo.ly/api/projects/"+idProject+".json");
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(3));

        response = given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body(bodyProject.toString())
                .log()
                .all()
                .when()
                .delete("https://todo.ly/api/projects/"+idProject+".json");
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Deleted",equalTo(true));
    }


}
