import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class GetUserTest {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final int MAX_RETRIES = 3;

    @Step("Intentar obtener el usuario con ID {0}, reintento #{1}")
    public void getUserConReintento(int userId, int attempt) {
        SerenityRest.given()
                .baseUri(BASE_URL)
                .log().all()
                .when()
                .get("/users/" + userId)
                .then()
                .log().all()
                .statusCode(anyOf(is(200), is(201))) // Acepta 200 o 201
                .body("data.id", equalTo(userId));
    }

    @Test
    public void ObtenerUsuarioConReintento() {
        int userId = 2;
        boolean success = false;

        for (int attempt = 1; attempt <= MAX_RETRIES && !success; attempt++) {
            try {
                getUserConReintento(userId, attempt);
                success = true;
            } catch (AssertionError e) {
                System.out.println("❌ Fallo en intento " + attempt + ": " + e.getMessage());
                if (attempt == MAX_RETRIES) {
                    throw e; // Si ya es el último intento, falla la prueba
                }
            }
        }
    }
}
