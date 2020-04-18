import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPlaceholderPostPhotosTest {

    @Test
    public void jsonPlaceholderCreateNewPhoto(){

        String jsonBody = "{\n" +
                "    \"albumId\": 1,\n" +
                "    \"title\": \"Kate titlet\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
                "  }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("http://jsonplaceholder.typicode.com/photos")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Kate titlet", json.get("title"));
        assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
    }
}
