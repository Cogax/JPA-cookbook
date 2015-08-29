package ch.cogax.jpacookbook.entity;

import java.util.Collection;

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

    @Column
    private int noOfPerson;

    @OneToMany
    private Collection<Ingredient> ingredients;

    @ElementCollection
    @CollectionTable(name = "tags")
    private Collection<String> tags;

    @OneToOne
    private Image image;
}
