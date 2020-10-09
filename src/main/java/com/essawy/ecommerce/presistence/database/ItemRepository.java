package com.essawy.ecommerce.presistence.database;

import com.essawy.ecommerce.presistence.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContains(String name);
    Page<Item> findByNameContains(String name, Pageable pageable);


}
