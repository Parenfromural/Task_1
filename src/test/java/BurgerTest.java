import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(100f);
        when(bunMock.getName()).thenReturn("Mock Bun");

        ingredientMock1 = mock(Ingredient.class);
        when(ingredientMock1.getPrice()).thenReturn(10f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("Кетчуп");

        ingredientMock2 = mock(Ingredient.class);
        when(ingredientMock2.getPrice()).thenReturn(100f);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("Говядина");
    }

    @Test
    public void setBunsStoresBun() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientAddsToList() {
        burger.addIngredient(ingredientMock1);
        assertTrue(burger.ingredients.contains(ingredientMock1));
    }

    @Test
    public void removeIngredientRemovesCorrectIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredientMock1));
        assertTrue(burger.ingredients.contains(ingredientMock2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientThrowsExceptionOnInvalidIndex() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientChangesOrder() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(1, 0);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientThrowsExceptionOnInvalidIndex() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void getPriceCalculatesCorrectly() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expected = 100f * 2 + 10f + 100f;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPriceReturnsZeroIfNoBun() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        try {
            float price = burger.getPrice();
            fail("Expected NullPointerException or price 0 if bun is null");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void getReceiptContainsBunName() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        String expectedBunLine = "(==== Mock Bun ====)";
        assertTrue(receipt.contains(expectedBunLine));
    }

    @Test
    public void getReceiptContainsIngredientDetails() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        String receipt = burger.getReceipt();
        String expectedIngredientLine = "= sauce Кетчуп =";
        assertTrue(receipt.toLowerCase().contains(expectedIngredientLine.toLowerCase()));
    }

    @Test
    public void getReceipt() {
        when(bunMock.getName()).thenReturn("Классическая булочка");
        when(bunMock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String expected = String.format(
                "(==== Классическая булочка ====)%n" +
                        "= sauce Кетчуп =%n" +
                        "= filling Говядина =%n" +
                        "(==== Классическая булочка ====)%n" +
                        "%nPrice: %f%n",
                50f * 2 + 10f + 100f
        );
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void getPriceCallsGetPriceOnIngredientsAndBun() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.getPrice();

        verify(bunMock, times(1)).getPrice();
        verify(ingredientMock1, times(1)).getPrice();
        verify(ingredientMock2, times(1)).getPrice();
    }
}