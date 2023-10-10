package itemsCRUD;

import ejercicios.JsonActions;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JsonActionsTest {
    @Test
    public void verifyCompareJSONs(){
        JSONObject expected = new JSONObject();
        expected.put("Id", "ignore");
        expected.put("Content", "Panqueques");
        expected.put("ItemType", 1);
        expected.put("Checked", false);
        expected.put("ProjectId", 4129350);
        expected.put("ParentId", "ignore");
        expected.put("Path", "");
        expected.put("Collapsed", false);
        expected.put("DateString", "ignore");
        expected.put("DateStringPriority", 0);
        expected.put("DueDate", "");
        expected.put("Recurrence", "ignore");
        expected.put("ItemOrder", "ignore");
        expected.put("Priority", 4);
        expected.put("LastSyncedDateTime", "ignore");
        expected.put("Children", "ignore");
        expected.put("DueDateTime", "ignore");
        expected.put("CreatedDate", "ignore");
        expected.put("LastCheckedDate", "ignore");
        expected.put("LastUpdatedDate", "ignore");
        expected.put("Deleted", false);
        expected.put("Notes", "ignore");
        expected.put("InHistory", false);
        expected.put("SyncClientCreationId", "ignore");
        expected.put("DueTimeSpecified", true);
        expected.put("OwnerId", 730629);


        String username = "ambarrojasm@gmail.com";
        String password = "Amys4n23";
        String urlPost = "https://todo.ly/api/items.json";

        JSONObject item = new JSONObject();
        item.put("Content", "Panqueques");
        item.put("ProjectId", 4129350);

        // Create Item
        Response response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(item.toString())
                .log()
                .all()
                .when()
                .post(urlPost);

        System.out.println(response);
        String actualBody = response.getBody().asString();
        JSONObject actual = new JSONObject(actualBody);



        JsonActions actions = new JsonActions();
        boolean resultExpected = true;
        boolean resultActual = actions.compareJSONs(expected, actual);
        Assertions.assertEquals(resultExpected, resultActual, "Error, valores erroneos en el item");
    }

    @Test
    public void verifyCompareJSONs2(){
        JSONObject expected = new JSONObject();
        expected.put("Id", "ignore");
        expected.put("Content", "awita de pan");
        expected.put("ItemType", 1);
        expected.put("Checked", true);
        expected.put("ProjectId", 4129350);
        expected.put("ParentId", "ignore");
        expected.put("Path", "");
        expected.put("Collapsed", false);
        expected.put("DateString", "ignore");
        expected.put("DateStringPriority", 0);
        expected.put("DueDate", "");
        expected.put("Recurrence", "ignore");
        expected.put("ItemOrder", "ignore");
        expected.put("Priority", 4);
        expected.put("LastSyncedDateTime", "ignore");
        expected.put("Children", "ignore");
        expected.put("DueDateTime", "ignore");
        expected.put("CreatedDate", "ignore");
        expected.put("LastCheckedDate", "ignore");
        expected.put("LastUpdatedDate", "ignore");
        expected.put("Deleted", false);
        expected.put("Notes", "ignore");
        expected.put("InHistory", true);
        expected.put("SyncClientCreationId", "ignore");
        expected.put("DueTimeSpecified", true);
        expected.put("OwnerId", 730629);


        String username = "ambarrojasm@gmail.com";
        String password = "Amys4n23";
        String urlPost = "https://todo.ly/api/items.json";

        JSONObject item = new JSONObject();
        item.put("Content", "awita de pan");
        item.put("ProjectId", 4129350);
        item.put("Checked", true);

        // Create Item
        Response response = given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(item.toString())
                .log()
                .all()
                .when()
                .post(urlPost);

        System.out.println(response);
        String actualBody = response.getBody().asString();
        JSONObject actual = new JSONObject(actualBody);



        JsonActions actions = new JsonActions();
        boolean resultExpected = true;
        boolean resultActual = actions.compareJSONs(expected, actual);
        Assertions.assertEquals(resultExpected, resultActual, "Error, valores erroneos en el item");
    }
}
