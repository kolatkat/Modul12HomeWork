//import com.sun.tools.javac.util.Assert;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hamcrest.CoreMatchers;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.StringUtils.substring;

public class JsonPlaceholderGETAllUserTest {

    private final String BASE_URL = "http://jsonplaceholder.typicode.com";
    private final String USERS = "users";
    private String domena = ".pl";

    @Test
    public void JsonPlaceholdeGETAllUser(){

        Response response = given()
                .when()
                .get(BASE_URL +"/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        System.out.println(emails);

        for( int i=0; i < emails.size(); i++) {

            String s1 = emails.get(i).substring(emails.get(i).length()-3);
            System.out.println(s1);
            System.out.println(s1.equals(domena));
            Assertions.assertFalse(s1.equals(domena));
        }
        
    }

    @Test
    public void jsonplaceholderReadUserWithQueryParams(){

        Response response = given()
                .queryParams("Id", 1)
                .when()
                .get(BASE_URL +"/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
     //    int  i = json.getList("email").get(0).size();
      //  String s1 = (json.getList("eamil").get(0)).substring(emails.get(i).length()-3);

        System.out.println(json.getList("email").get(0));

        Assertions.assertNotEquals(domena, json.getList("email").get(0));
        //Assertions.assertEquals(  "accusamus beatae ad facilis cum similique qui sunt", json.getList("title").get(0));
        //Assertions.assertEquals(  "https://via.placeholder.com/600/92c952", json.getList("url").get(0));
    }

//        String s1 = "asdlio@jjsds.pl";
//        String s2 = "retsfdGDS@onet.com";
//        String s3 = "ertdsavfxsfas@wp.gl";
//
//        String substr1 = s1.substring(s1.length()-3);
//        String substr2 = s2.substring(s2.length()-3);
//        String substr3 = s3.substring(s3.length()-3);
//
//        System.out.println(substr1);
//        System.out.println(substr2);
//        System.out.println(substr3);
//
}