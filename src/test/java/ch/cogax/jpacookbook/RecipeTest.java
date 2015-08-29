package ch.cogax.jpacookbook;

import org.junit.Assert;
import org.junit.Test;

import ch.cogax.jpacookbook.entity.Image;
import ch.cogax.jpacookbook.entity.Ingredient;
import ch.cogax.jpacookbook.entity.Recipe;
import ch.cogax.jpacookbook.persistence.IRecipeRepository;
import ch.cogax.jpacookbook.persistence.jpa.RecipeRepository;

public class RecipeTest {

    @Test
    public void testAddARecipe() {
	IRecipeRepository repo = new RecipeRepository("testDB");
	repo.save(getRecipe("test"));
	Assert.assertTrue(repo.findByTitle("test").size() > 0);
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
