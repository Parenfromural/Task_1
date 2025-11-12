import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void ingredientGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Test Sauce", 50f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void ingredientGetNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Test Sauce", 50f);
        assertEquals("Test Sauce", ingredient.getName());
    }

    @Test
    public void ingredientGetPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Test Sauce", 50f);
        assertEquals(50f, ingredient.getPrice(), 0.001f);
    }
}