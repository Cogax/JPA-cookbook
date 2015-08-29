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

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
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

    @Column(nullable = false, length = 250)
    private String url;

    @Column(nullable = true, length = 250)
    private String description;
}
