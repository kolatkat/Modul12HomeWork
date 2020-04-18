import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderGetPhotosTwoTest {

    private final String BASE_URL = "http://jsonplaceholder.typicode.com";
    private final String PHOTOS = "photos";

    //GIVEN - konfiguracja
    //WHEN  - wysłanie requestu
    //THEN - asercja

    @Test
    public void JsonPlaceholderReadAllPhotos() {

        Response response = given()
                .when()
                .get(BASE_URL +"/" + PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        //System.out.println(response.asString());

       // Assertions.assertEquals(200,response.statusCode());

        JsonPath json = response.jsonPath();

        List<Object> titles = json.getList("title");

//        System.out.println(titles.size());

        //titles.stream()
        //       .forEach(System.out::println);

        Assertions.assertEquals(5000,titles.size());

    }

    @Test
    public void JsonPlaceholderReadOnePhoto(){

        Response response = given()
                .when()
                .get(BASE_URL +"/" + PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
//
//        System.out.println(response.asString());
//
        JsonPath json = response.jsonPath();
//
        Assertions.assertEquals(  "accusamus beatae ad facilis cum similique qui sunt", json.get("title"));
        Assertions.assertEquals(  "https://via.placeholder.com/600/92c952", json.get("url"));
    }

    // PATH VARIABLES

    @Test
    public void jsonPlaceholderReadOneUserWithPathVariables(){

        Response response = given()
                .pathParam("photoId", 1)
                .when()
                .get(BASE_URL +"/" + PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

       // Assertions.assertEquals(200, response.statusCode());
    }

    // QUERY PARAMS

    @Test
    public void jsonplaceholderReadPhotosWithQueryParams(){

        Response response = given()
                .queryParams("photoId", 1)
                .when()
                .get(BASE_URL +"/" + PHOTOS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(  "accusamus beatae ad facilis cum similique qui sunt", json.getList("title").get(0));
        Assertions.assertEquals(  "https://via.placeholder.com/600/92c952", json.getList("url").get(0));
    }
}

