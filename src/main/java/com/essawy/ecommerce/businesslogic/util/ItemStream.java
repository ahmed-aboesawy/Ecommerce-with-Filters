package com.essawy.ecommerce.businesslogic.util;

import com.essawy.ecommerce.presistence.models.Item;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public interface ItemStream {

    ItemStream isActive();
    ItemStream withPrice(BigDecimal price);
    ItemStream activeWithPrice(BigDecimal price);
    List<Item> getItems();
    Page<Item> getItemPages();


    static ItemStreamImpl stream(Stream<Item> stream){
        return new ItemStreamImpl(stream);
    }

}
