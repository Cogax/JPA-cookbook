package ch.cogax.jpacookbook;

import java.util.ArrayList;

import ch.cogax.jpacookbook.entity.Image;
import ch.cogax.jpacookbook.entity.Ingredient;
import ch.cogax.jpacookbook.entity.Recipe;
import ch.cogax.jpacookbook.persistence.IRecipeRepository;
import ch.cogax.jpacookbook.persistence.jpa.RecipeRepository;

public class App {
    public static void main(String[] args) {
	System.out.println("Setup Repository to save our recipes..");
	IRecipeRepository repo = new RecipeRepository("liveDB");
	System.out.println("Done.");

	Recipe recipe = getRecipe("My new Recipe");
	System.out.println("Setup a new Recipe with following properties:");
	System.out.println("Title: " + recipe.getTitle());
	System.out.println("Preample: " + recipe.getPreample());
	System.out.println("Preparation: " + recipe.getPreparation());
	System.out.println("Number of Persons: " + recipe.getNoOfPerson());
	String tags = "Tags: ";
	for (String tag : recipe.getTags()) {
	    tags += tag + " ";
	}
	System.out.println(tags);
	System.out.println("Image URL: " + recipe.getImage().getUrl());
	System.out.println("Image Description: "
		+ recipe.getImage().getDescription());
	System.out.println("Ingredients: " + recipe.getIngredients().size());
	ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(
		recipe.getIngredients());
	for (Ingredient ingretient : ingredients) {
	    System.out
		    .println("\t Description: " + ingretient.getDescription());
	    System.out.println("\t Comment: " + ingretient.getComment());
	    System.out.println("\t Quantity: " + ingretient.getQuantity());

	}

	System.out.println("Add Recipe to Repository..");
	repo.save(recipe);
	System.out.println("Done.");

	System.out.println("Load Recipe from Repository with title '"
		+ recipe.getTitle() + "'..");
	Recipe loadedRecipe = new ArrayList<Recipe>(repo.findByTitle(recipe
		.getTitle())).get(0);
	System.out.println("Done.");

	System.out.println("Change title to 'Neuer Titel' and update DB..");
	loadedRecipe.setTitle("Neuer Titel");
	repo.save(recipe);
	System.out.println("Done.");

	System.out.println("Load Recipe by it's new Title..");
	Recipe updatedRecipe = new ArrayList<Recipe>(
		repo.findByTitle(loadedRecipe.getTitle())).get(0);
	System.out.println("Updated Receipe's Title is: '"
		+ updatedRecipe.getTitle() + "'");

	System.out.println("Delete Receipe..");
	repo.delete(updatedRecipe);
	System.out.println("Done.");
    }

    private static Recipe getRecipe(String title) {
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
