package com.essawy.ecommerce.businesslogic.domain;

import com.essawy.ecommerce.presistence.models.Category;
import com.essawy.ecommerce.presistence.models.Item;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomItem {

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Boolean active;
    private Category category;

    public Item toItem(){
        Item item = new Item();
        item.setName(this.name);
        item.setDescription(this.description);
        item.setImageUrl(this.imageUrl);
        item.setPrice(this.price);
        item.setActive(this.active);
        item.setCategory(this.category);
        return item;
    }

}








