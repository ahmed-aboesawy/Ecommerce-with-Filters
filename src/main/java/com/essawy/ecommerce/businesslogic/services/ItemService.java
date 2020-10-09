package com.essawy.ecommerce.businesslogic.services;

import com.essawy.ecommerce.businesslogic.domain.CustomItem;
import com.essawy.ecommerce.businesslogic.util.ItemStream;
import com.essawy.ecommerce.presistence.database.ItemRepository;
import com.essawy.ecommerce.presistence.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Page<Item> findAllWithFilters(String name, Boolean active, BigDecimal price,Pageable pageable) {
        Page<Item> pages = applyFilters(this.repository.findByNameContains(name, pageable), active, price);
        return pages;
    }

    private Page<Item> applyFilters(Page<Item> pages, Boolean active, BigDecimal price) {
        ItemStream itemStream = ItemStream.stream(pages.stream());
        return (active && price != null)  ? itemStream.activeWithPrice(price).getItemPages()
                :(!active && price != null)  ? itemStream.withPrice(price).getItemPages()
                :(active && price == null)  ? itemStream.isActive().getItemPages()
                : itemStream.getItemPages();
    }

    public Item findById(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() ->  new NoSuchElementException("No such item with id: " + id));
    }

    public Item save(CustomItem item) {
        return this.repository.save(item.toItem());
    }

    public Item fullUpdate(Long id, CustomItem customItem){
        findById(id);
        Item item = customItem.toItem();
        item.setId(id);
        return this.repository.save(item);
    }

    public Item updatePart(Long id, CustomItem customItem){
        Item item = findById(id);
        if (customItem.getCategory() != null)
            item.setCategory(customItem.getCategory());
        if (customItem.getName() != null)
            item.setName(customItem.getName());
        if (customItem.getDescription() != null)
            item.setDescription(customItem.getDescription());
        if (customItem.getActive() != null)
            item.setActive(customItem.getActive());
        if (customItem.getPrice() != null)
            item.setPrice(customItem.getPrice());
        if (customItem.getImageUrl() != null)
            item.setImageUrl(customItem.getImageUrl());

        return this.repository.save(item);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
