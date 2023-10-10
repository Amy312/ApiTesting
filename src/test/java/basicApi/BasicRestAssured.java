package basicApi;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured {
    /*
    * given() -> conf > header / params / body / Auth
    * when() -> method (put, post, get, delete) >url petition and response
    * then() -> response -> body / code / msg / headers / time //tenemos toda la info
    * */

    @Test
    public void createProjectByApi(){
        given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body("{\n" +
                        " \"Content\":\"Ambar REST\",\n" +
                        " \"Icon\":4 \n" +
                        "}")
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json").
        then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo("Ambar REST"))
                .body("Icon",equalTo(4));
    }

    @Test
    public void createProjectByApiWithJsonObject(){
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "Amy Json");
        bodyProject.put("Icon", 3);
        given()
                .auth()
                .preemptive()
                .basic("ambarrojasm@gmail.com", "Amys4n23")
                .body(bodyProject.toString())
                .log()
                .all().
                when()
                .post("https://todo.ly/api/projects.json").
                then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo("Amy Json"))
                .body("Icon",equalTo(3));
    }
}
