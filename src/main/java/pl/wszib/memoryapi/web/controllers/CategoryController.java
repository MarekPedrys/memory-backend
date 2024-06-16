package pl.wszib.memoryapi.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wszib.memoryapi.services.CategoryService;
import pl.wszib.memoryapi.web.models.CategoryRequest;
import pl.wszib.memoryapi.web.models.CategoryResponse;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> categoriesList(){
        List<CategoryResponse> categoryList = categoryService.fetchAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> singleCategory(@PathVariable Long categoryId){
        CategoryResponse categoryResponse = categoryService.fetchCategory(categoryId);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> newCategory(@RequestBody CategoryRequest request) {
        CategoryResponse createdCategory = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoryService.removeCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}