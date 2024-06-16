package pl.wszib.memoryapi.data.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String term;
    private String definition;
    private Date createdDate;

    public CardEntity(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public CardEntity() {
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDefinition() {
        return definition;
    }
}