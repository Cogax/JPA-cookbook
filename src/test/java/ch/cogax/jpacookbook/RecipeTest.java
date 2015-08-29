package ch.cogax.jpacookbook;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.cogax.jpacookbook.entity.Image;
import ch.cogax.jpacookbook.entity.Ingredient;
import ch.cogax.jpacookbook.entity.Recipe;
import ch.cogax.jpacookbook.persistence.IRecipeRepository;
import ch.cogax.jpacookbook.persistence.jpa.RecipeRepository;

public class RecipeTest {
    private static IRecipeRepository repo;

    @BeforeClass
    public static void setUp() {
	repo = new RecipeRepository("testDB");
    }

    public static void tearDown() {
	repo.close();
    }

    @Test
    public void testAddARecipe_FindByTitleSizeIsMoreThanZero() {
	repo.save(getRecipe("test1"));

	ArrayList<Recipe> recipesFoundByTitle = new ArrayList<Recipe>(
		repo.findByTitle("test1"));

	Assert.assertNotNull(recipesFoundByTitle);
	Assert.assertTrue(recipesFoundByTitle.size() > 0);
    }

    @Test
    public void testAddMultipleRecipesWithSameTitle_FindByTitleSizeIsCorrect() {

    }

    private Recipe getRecipe(String title) {
	Recipe recipe = new Recipe();
	recipe.setTitle(title);
	recipe.addTag("keyword1");
	recipe.addTag("keyword2");
	recipe.setPreample("keine Ahnung was preample ist");
	recipe.setPreparation("Feuerlöscher bereit halten!");
	recipe.setNoOfPerson(4);

	Image image = new Image();
	image.setDescription("Google Logo");
	image.setUrl("https://www.google.ch/images/srpr/logo11w.png");
	recipe.setImage(image);

	for (int i = 0; i <= 10; i++) {
	    Ingredient ingredient = new Ingredient();
	    ingredient.setComment("Testkommentar " + i);
	    ingredient.setDescription("Testbeschreibung " + i);
	    ingredient.setQuantity(i + "Esslöffel");
	    recipe.addIngredients(ingredient);
	}

	return recipe;
    }
}
