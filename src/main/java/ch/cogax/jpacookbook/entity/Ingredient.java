package ch.cogax.jpacookbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 250)
    private String quantity;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = true, length = 250)
    private String comment;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
	return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(String quantity) {
	this.quantity = quantity;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return the comment
     */
    public String getComment() {
	return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
	this.comment = comment;
    }
}
