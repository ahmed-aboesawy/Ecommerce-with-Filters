package com.essawy.ecommerce.web;

import com.essawy.ecommerce.businesslogic.domain.CustomItem;
import com.essawy.ecommerce.businesslogic.services.ItemService;
import com.essawy.ecommerce.presistence.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Page<Item> getAllItemsContains(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "active", required = false, defaultValue = "false")  Boolean active,
            @RequestParam(name = "price", required = false) BigDecimal price,
            Pageable pageable){
        return this.itemService.findAllWithFilters(name,active, price, pageable);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable(name = "id") Long id){
        return this.itemService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody CustomItem item){
        return ResponseEntity.ok(this.itemService.save(item));
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody CustomItem item){
        return ResponseEntity.ok(this.itemService.fullUpdate(id, item));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Item> updatePart(@PathVariable Long id, @RequestBody CustomItem item){
        return ResponseEntity.ok(this.itemService.updatePart(id, item));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> deleteCategory(@PathVariable Long id){
        this.itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}



