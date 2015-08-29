package ch.cogax.jpacookbook.persistence.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ch.cogax.jpacookbook.entity.Recipe;
import ch.cogax.jpacookbook.persistence.IRecipeRepository;

public class RecipeRepository implements IRecipeRepository {
    private EntityManager entityManager;

    public RecipeRepository(String db) {
	EntityManagerFactory entityManagerFactory = Persistence
		.createEntityManagerFactory(db);
	entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Collection<Recipe> findByTitle(String title) {
	final TypedQuery<Recipe> query = entityManager.createQuery(
		"SELECT r from Recipe r WHERE r.title LIKE :title",
		Recipe.class).setParameter("title", title);
	return query.getResultList();
    }

    @Override
    public Collection<Recipe> findAll() {
	final TypedQuery<Recipe> query = entityManager.createQuery(
		"SELECT r from Recipe r", Recipe.class);
	return query.getResultList();
    }

    @Override
    public Recipe findById(Long id) {
	final TypedQuery<Recipe> query = entityManager.createQuery(
		"SELECT r from Recipe r WHERE r.id = :id", Recipe.class)
		.setParameter("id", id);
	return query.getSingleResult();
    }

    @Override
    public void save(Recipe entity) {
	entityManager.getTransaction().begin();
	if (entityManager.contains(entity)) {
	    entityManager.merge(entity);
	} else {
	    entityManager.persist(entity);
	}
	entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Recipe entity) {
	entityManager.getTransaction().begin();
	entityManager.remove(entity);
	entityManager.getTransaction().commit();
    }

    @Override
    public void close() {
	entityManager.close();
    }
}
