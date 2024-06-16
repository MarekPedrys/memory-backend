package pl.wszib.memoryapi.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.wszib.memoryapi.data.entities.CardEntity;
import pl.wszib.memoryapi.data.entities.CategoryEntity;
import pl.wszib.memoryapi.data.repositories.CardRepository;
import pl.wszib.memoryapi.data.repositories.CategoryRepository;
import pl.wszib.memoryapi.web.models.CardRequest;
import pl.wszib.memoryapi.web.models.CardResponse;

import java.util.List;

@Service
public class CardService {

    public CardService(CardRepository cardRepository, CategoryRepository categoryRepository) {
        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
    }

    private final CardRepository cardRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public CardResponse createCard(Long categoryId, CardRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);
        CardEntity cardEntity = new CardEntity(request.term(), request.definition());
        CardEntity savedCard = cardRepository.save(cardEntity);
        categoryEntity.addCard(savedCard);
        return new CardResponse(categoryId, savedCard);
    }

    public List<CardResponse> fetchAll(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        return categoryEntity.getCards().stream()
                .map(c -> new CardResponse(categoryId, c))
                .toList();
     }

//     @Transactional
//    public void removeCard(Long categoryId, Long cardId) {
//        cardRepository.findByIdAndCategoryId(cardId,categoryId).ifPresent(cardRepository::delete);
//    }

    @Transactional
    public void removeCard(Long categoryId, Long cardId) {
        categoryRepository.findById(categoryId).ifPresent(category -> category.removeCard(cardId));
    }



}
