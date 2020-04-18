import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class JsonPlaceholderDELETETest {

    @Test
    public  void jsonplaceholderDELETEPhoto(){

        Response response = given()
                .when()
                .delete("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
