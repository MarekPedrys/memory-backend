package pl.wszib.memoryapi.data.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "categoryId")
    private Set<CardEntity> cards = new HashSet<>();

    public void removeCard(Long cardId) {
        cards.removeIf(card -> card.getId().equals(cardId));
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public CategoryEntity() {
    }

    @PrePersist
    public void prePersist(){
        createdDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void addCard(CardEntity card) {
        this.cards.add(card);
    }

    public Set<CardEntity> getCards() {
        return Set.copyOf(cards);
    }


}