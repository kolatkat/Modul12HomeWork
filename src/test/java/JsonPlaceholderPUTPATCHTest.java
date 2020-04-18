import com.github.javafaker.Faker;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderPUTPATCHTest{

    private static Faker faker;
    private String faketitle;
    private String fakeurl;

    @BeforeAll
    public static void beforeAll(){
        faker = new Faker();

    }

    @BeforeEach
    public void beforeEach(){
        faketitle = faker.name().title();
        fakeurl = faker.internet().url();
    }

    @Test
    public void jsonPlaceholderUpdatePhotoPUTTest(){

        JSONObject photo = new JSONObject();
        photo.put("albumId",1);
        photo.put("id",1);
        photo.put("title",faketitle);
        photo.put("url",fakeurl);
        photo.put("thumbnailUrl","https://via.placeholder.com/600/92c952");

        System.out.println(photo.toString());

//        String jsonBody = "{\n" +
//                "  \"albumId\": 1,\n" +
//                "  \"id\": 1,\n" +
//                "  \"title\": \"accusamus beatae ad facilis cum similique qui sunt PUT\",\n" +
//                "  \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
//                "  \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
//                "}";

        Response response = given()
               .contentType("application/json")
                .body(photo.toString())
                .when()
                .put("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .statusCode(200)
                .extract()
                .response();


        JsonPath json = response.jsonPath();
        Assertions.assertEquals(faketitle, json.get("title"));
        Assertions.assertEquals(fakeurl, json.get("url"));
    }

    @Test
    public void jsonPlaceholderUpdatePhotoPATCHTest() {

        JSONObject photo = new JSONObject();
        photo.put("title",faketitle);


//        String jsonBody = "{\n" +
//        "  \"title\": \"accusamus beatae ad facilis cum similique qui sunt PUT\"\n" +"}";

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .patch("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(faketitle, json.get("title"));
    }
}
