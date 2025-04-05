package main.najah.test;

import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Recipe Book Tests")
public class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
    }

    //test 1:verify recipe addition with explicit package
    @Test
    @DisplayName("add valid recipe")
    void testAddRecipe() throws RecipeException {
        main.najah.code.Recipe recipe = createValidRecipe();
        assertTrue(recipeBook.addRecipe(recipe));
    }

    //test 2: verify duplicate prevention
    @Test
    @DisplayName("prevent duplicate recipes")
    void testDuplicateRecipe() throws RecipeException {
        main.najah.code.Recipe recipe1 = createValidRecipe();
        main.najah.code.Recipe recipe2 = createValidRecipe();

        recipeBook.addRecipe(recipe1);
        assertFalse(recipeBook.addRecipe(recipe2));
    }

    private main.najah.code.Recipe createValidRecipe() throws RecipeException {
        main.najah.code.Recipe recipe = new main.najah.code.Recipe();
        recipe.setName("coffee");
        recipe.setPrice("5");
        recipe.setAmtCoffee("3");
        return recipe;
    }
}