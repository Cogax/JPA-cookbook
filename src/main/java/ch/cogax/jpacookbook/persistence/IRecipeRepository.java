package ch.cogax.jpacookbook.persistence;

import java.util.Collection;

import ch.cogax.jpacookbook.entity.Recipe;

public interface IRecipeRepository {
    public Collection<Recipe> findByTitle(String title);

    public Collection<Recipe> findAll();

    public Recipe findById(Long id);

    public void save(Recipe entity);

    public void delete(Recipe entity);

    public void close();
}
