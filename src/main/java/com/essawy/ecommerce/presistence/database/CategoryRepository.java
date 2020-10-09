package com.essawy.ecommerce.presistence.database;

import com.essawy.ecommerce.presistence.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
