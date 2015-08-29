package ch.cogax.jpacookbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 250)
    private String url;

    @Column(nullable = true, length = 250)
    private String description;
}
