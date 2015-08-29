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
    public void testAddARecipe_FindByTitleSizeIsOne() {
	repo.save(getRecipe("test1"));

	ArrayList<Recipe> recipesFoundByTitle = new ArrayList<Recipe>(
		repo.findByTitle("test1"));

	Assert.assertNotNull(recipesFoundByTitle);
	Assert.assertTrue(recipesFoundByTitle.size() == 1);
    }

    @Test
    public void testAddTwoRecipesWithSameTitle_FindByTitleSizeIsTwo() {
	repo.save(getRecipe("test2"));
	repo.save(getRecipe("test2"));

	ArrayList<Recipe> recipesFoundByTitle = new ArrayList<Recipe>(
		repo.findByTitle("test2"));

	Assert.assertNotNull(recipesFoundByTitle);
	Assert.assertTrue(recipesFoundByTitle.size() == 2);
    }

    @Test
    public void testAddThreeRecipes_FindAllReturnsThreeRecipes() {
	repo.save(getRecipe("test3-1"));
	repo.save(getRecipe("test3-2"));
	repo.save(getRecipe("test3-3"));

	ArrayList<Recipe> recipes = new ArrayList<Recipe>(repo.findAll());

	Assert.assertEquals(3, recipes.size());
    }

    @Test
    public void testAddRecipes_TestFindById() {
	repo.save(getRecipe("test4-1"));
	repo.save(getRecipe("test4-2"));
	repo.save(getRecipe("test4-3"));

	ArrayList<Recipe> recipes = new ArrayList<Recipe>(repo.findAll());

	for (Recipe recipe : recipes) {
	    Assert.assertEquals(recipe, repo.findById(recipe.getId()));
	}
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
