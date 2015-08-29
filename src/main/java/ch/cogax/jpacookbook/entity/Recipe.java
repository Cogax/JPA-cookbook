package ch.cogax.jpacookbook.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = true, length = 250)
    private String preample;

    @Column(nullable = true, length = 250)
    private String preparation;

    @Column(nullable = false)
    private int noOfPerson;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Collection<Ingredient> ingredients = new ArrayList<Ingredient>();

    @ElementCollection
    @CollectionTable(name = "tags")
    private Set<String> tags = new HashSet<String>();

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Image image;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * @return the preample
     */
    public String getPreample() {
	return preample;
    }

    /**
     * @param preample
     *            the preample to set
     */
    public void setPreample(String preample) {
	this.preample = preample;
    }

    /**
     * @return the preparation
     */
    public String getPreparation() {
	return preparation;
    }

    /**
     * @param preparation
     *            the preparation to set
     */
    public void setPreparation(String preparation) {
	this.preparation = preparation;
    }

    /**
     * @return the noOfPerson
     */
    public int getNoOfPerson() {
	return noOfPerson;
    }

    /**
     * @param noOfPerson
     *            the noOfPerson to set
     */
    public void setNoOfPerson(int noOfPerson) {
	this.noOfPerson = noOfPerson;
    }

    /**
     * @return the ingredients
     */
    public Collection<Ingredient> getIngredients() {
	return ingredients;
    }

    /**
     * @param ingredient
     *            the ingredient to add
     */
    public void addIngredients(Ingredient ingredient) {
	this.ingredients.add(ingredient);
    }

    /**
     * @return the tags
     */
    public Collection<String> getTags() {
	return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void addTag(String tag) {
	this.tags.add(tag);
    }

    /**
     * @return the image
     */
    public Image getImage() {
	return image;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(Image image) {
	this.image = image;
    }

}
