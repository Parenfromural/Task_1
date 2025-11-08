import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private String name;
    private IngredientType expected;

    public IngredientTypeTest(String name, IngredientType expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: valueOf({0})={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "SAUCE", IngredientType.SAUCE },
                { "FILLING", IngredientType.FILLING }
        });
    }

    @Test
    public void testValueOf() {
        assertEquals(expected, IngredientType.valueOf(name));
    }
}