package users;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteUserTest {

    @Test
    public void deleteUser_shouldReturn200() {
        SerenityRest
                .given()
                .when()
                .delete(("/users/1"))
                .then()
                .statusCode(200);
    }
}
