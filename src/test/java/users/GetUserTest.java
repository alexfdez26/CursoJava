package users;

import models.Usuario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class GetUserTest {

    private EnvironmentVariables environmentVariables;

    @Test
    public void getUser_shouldReturnUserData() {
        String baseUrl = EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("serenity.rest.base.url");

        Usuario usuario = SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Usuario.class);

        // Validaciones básicas
        assertThat(usuario.getId(), equalTo(1));
        assertThat(usuario.getName(), equalTo("Leanne Graham"));
        assertThat(usuario.getUsername(), equalTo("Bret"));
        assertThat(usuario.getEmail(), equalTo("Sincere@april.biz"));

        // Address
        assertThat(usuario.getAddress(), notNullValue());
        assertThat(usuario.getAddress().getStreet(), equalTo("Kulas Light"));
        assertThat(usuario.getAddress().getCity(), equalTo("Gwenborough"));
        assertThat(usuario.getAddress().getZipcode(), equalTo("92998-3874"));

        // Geo
        assertThat(usuario.getAddress().getGeo(), notNullValue());
        assertThat(usuario.getAddress().getGeo().getLat(), equalTo("-37.3159"));
        assertThat(usuario.getAddress().getGeo().getLng(), equalTo("81.1496"));

        // Phone
        assertThat(usuario.getPhone(), equalTo("1-770-736-8031 x56442"));

        // Website
        assertThat(usuario.getWebsite(), equalTo("hildegard.org"));

        // Company
        assertThat(usuario.getCompany(), notNullValue());
        assertThat(usuario.getCompany().getName(), equalTo("Romaguera-Crona"));
        assertThat(usuario.getCompany().getCatchPhrase(), equalTo("Multi-layered client-server neural-net"));
        assertThat(usuario.getCompany().getBs(), equalTo("harness real-time e-markets"));
    }
}
