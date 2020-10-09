package com.essawy.ecommerce.businesslogic.services;

import com.essawy.ecommerce.presistence.database.CategoryRepository;
import com.essawy.ecommerce.presistence.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<Category> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Category findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such category with id: " + id)
        );
    }

    public Category save(Category item) {
        return this.repository.save(item);
    }

    public Category update(Long id, Category pCategory){
        Category category = findById(id);
        if ( !( pCategory.getName().isEmpty() ) )
            category.setName(pCategory.getName());

        return this.repository.save(category);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
