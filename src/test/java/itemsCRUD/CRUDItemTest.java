package itemsCRUD;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItemTest {
    @Test
    public  void crudItem(){
        String username = "ambarrojasm@gmail.com";
        String password = "Amys4n23";
        String urlPost = "https://todo.ly/api/items.json";

        JSONObject bodyItem = new JSONObject();
        bodyItem.put("Content", "Item de amy");

        // Create Item
        Response response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(bodyItem.toString())
                .log()
                .all()
        .when()
                .post(urlPost);

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyItem.get("Content")));

        int idItem = response.then().extract().path("Id");
        String urlRUD = "https://todo.ly/api/items/"+ idItem + ".json";

        // Read Item
        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(bodyItem.toString())
                .log()
                .all()
                .when()
                .get(urlRUD);
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")));

        // Update Item
        bodyItem.put("Content", "Update2 de item de Amy");
        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(bodyItem.toString())
                .log()
                .all()
                .when()
                .put(urlRUD);
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("Id", equalTo(idItem));

        // Delete Item
        response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(bodyItem.toString())
                .log()
                .all()
                .when()
                .delete(urlRUD);
        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("Deleted",equalTo(true));
    }
}
