package pl.wszib.memoryapi.services;

import org.springframework.stereotype.Service;
import pl.wszib.memoryapi.data.entities.CategoryEntity;
import pl.wszib.memoryapi.data.repositories.CategoryRepository;
import pl.wszib.memoryapi.web.models.CategoryRequest;
import pl.wszib.memoryapi.web.models.CategoryResponse;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        CategoryEntity categoryEntity = new CategoryEntity(request.name());
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return new CategoryResponse(savedCategory);
    }

    public List<CategoryResponse> fetchAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return entities.stream()
                .map(CategoryResponse::new)
                .toList();
    }

    public CategoryResponse fetchCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryResponse::new)
                .orElseThrow(NotFoundException::new);
    }

    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
