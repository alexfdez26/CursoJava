package demo;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class DemoTest {

    @Test
    public void prueba_exitosa() {
        assertThat(2 + 2).isEqualTo(4);
    }

//    @Test
//    public void prueba_fallida() {
//        assertThat("Hola").isEqualTo("Chao");
//    }
}
