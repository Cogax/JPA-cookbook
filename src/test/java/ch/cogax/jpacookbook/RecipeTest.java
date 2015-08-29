package ch.cogax.jpacookbook;

import java.util.ArrayList;

import org.junit.AfterClass;
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

    @AfterClass
    public static void tearDown() {
	repo.close();
    }

    @Test
    public void testAddRecipe_TestRecipeProperties() {
	Recipe r = getRecipe("test0");
	repo.save(r);

	Recipe recipe = new ArrayList<Recipe>(repo.findByTitle("test0")).get(0);

	Assert.assertEquals(r.getTitle(), recipe.getTitle());
	Assert.assertEquals(r.getPreample(), recipe.getPreample());
	Assert.assertEquals(r.getPreparation(), recipe.getPreparation());
	Assert.assertEquals(r.getTags().size(), recipe.getTags().size());
	for (String tag : r.getTags()) {
	    Assert.assertTrue(recipe.getTags().contains(tag));
	}
	Assert.assertEquals(r.getNoOfPerson(), recipe.getNoOfPerson());
	Assert.assertEquals(r.getImage().getDescription(), recipe.getImage()
		.getDescription());
	Assert.assertEquals(r.getImage().getUrl(), recipe.getImage().getUrl());
	Assert.assertEquals(r.getIngredients().size(), recipe.getIngredients()
		.size());
	ArrayList<Ingredient> oldIngredients = new ArrayList<Ingredient>(
		r.getIngredients());
	ArrayList<Ingredient> newIngredients = new ArrayList<Ingredient>(
		recipe.getIngredients());
	Assert.assertEquals(oldIngredients.get(0).getComment(), newIngredients
		.get(0).getComment());
	Assert.assertEquals(oldIngredients.get(0).getDescription(),
		newIngredients.get(0).getDescription());
	Assert.assertEquals(oldIngredients.get(5).getQuantity(), newIngredients
		.get(5).getQuantity());
	Assert.assertEquals(oldIngredients.get(5).getComment(), newIngredients
		.get(5).getComment());
	Assert.assertEquals(oldIngredients.get(5).getDescription(),
		newIngredients.get(5).getDescription());
	Assert.assertEquals(oldIngredients.get(5).getQuantity(), newIngredients
		.get(5).getQuantity());
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

	Assert.assertNotNull(recipes);
	for (Recipe recipe : recipes) {
	    Assert.assertEquals(recipe, repo.findById(recipe.getId()));
	}
    }

    @Test
    public void testUpdate() {
	repo.save(getRecipe("test5"));

	ArrayList<Recipe> recipes = new ArrayList<Recipe>(
		repo.findByTitle("test5"));

	Assert.assertEquals(1, recipes.size());

	Recipe recipe = recipes.get(0);
	recipe.setTitle("test5-neu");
	repo.save(recipe);

	Assert.assertEquals(0, repo.findByTitle("test5").size());
	Assert.assertEquals(1, repo.findByTitle("test5-neu").size());
    }

    @Test
    public void testDelete() {
	repo.save(getRecipe("test6"));

	ArrayList<Recipe> recipes = new ArrayList<Recipe>(
		repo.findByTitle("test6"));

	Assert.assertEquals(1, recipes.size());

	Recipe recipe = recipes.get(0);
	repo.delete(recipe);

	Assert.assertEquals(0, repo.findByTitle("test6").size());
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
