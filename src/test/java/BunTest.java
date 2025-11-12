import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void bunGetNameReturnsCorrectName() {
        Bun bun = new Bun("Test Bun", 123.45f);
        assertEquals("Test Bun", bun.getName());
    }

    @Test
    public void bunGetPriceReturnsCorrectPrice() {
        Bun bun = new Bun("Test Bun", 123.45f);
        assertEquals(123.45f, bun.getPrice(), 0.001f);
    }
}