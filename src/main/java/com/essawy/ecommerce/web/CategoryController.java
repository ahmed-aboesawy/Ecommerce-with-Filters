package com.essawy.ecommerce.web;

import com.essawy.ecommerce.businesslogic.services.CategoryService;
import com.essawy.ecommerce.presistence.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Category> getCategories(Pageable pageable){
        return this.service.findAll(pageable);
    }


    @PostMapping
    public ResponseEntity<Category> addItem(@RequestBody Category category){
        return ResponseEntity.ok(this.service.save(category));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Category> updatePart(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(this.service.update(id, category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
