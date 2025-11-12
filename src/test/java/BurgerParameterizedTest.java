import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock;
    private float expectedPrice;

    private String bunName;
    private float bunPrice;
    private IngredientType ingredientType;
    private String ingredientName;
    private float ingredientPrice;

    public BurgerParameterizedTest(String bunName, float bunPrice,
                                   IngredientType ingredientType, String ingredientName, float ingredientPrice,
                                   float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: bun={0}({1}), ingredient={3}({4}), expectedPrice={5}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Mock Bun", 100f, IngredientType.SAUCE, "Mock Sauce", 50f, 250f},
                {"Test Bun", 120f, IngredientType.FILLING, "Test Filling", 30f, 270f},
                {"Cheap Bun", 50f, IngredientType.SAUCE, "Cheap Sauce", 20f, 120f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptContainsBunNameAndIngredient() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains(ingredientName));
        assertTrue(receipt.contains(ingredientType.toString().toLowerCase()));
    }
}