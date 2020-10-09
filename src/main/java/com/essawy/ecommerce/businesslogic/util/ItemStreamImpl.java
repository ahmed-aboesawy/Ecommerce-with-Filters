package com.essawy.ecommerce.businesslogic.util;

import com.essawy.ecommerce.presistence.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemStreamImpl implements ItemStream{
    private final Stream<Item> stream;

    public ItemStreamImpl(Stream<Item> stream) {
        this.stream = stream;
    }

    @Override
    public ItemStream isActive() {
        return new ItemStreamImpl(this.stream.filter(Item::isActive));
    }

    @Override
    public ItemStream withPrice(BigDecimal price) {
        return new ItemStreamImpl(this.stream.filter(item -> item.getPrice().compareTo(price) <= 0));
    }

    @Override
    public ItemStream activeWithPrice(BigDecimal price) {
        return ItemStream.stream(this.stream).isActive().withPrice(price);
    }

    @Override
    public List<Item> getItems() {
        return this.stream.collect(Collectors.toList());
    }

    @Override
    public Page<Item> getItemPages() {
        return new PageImpl<>(this.stream.collect(Collectors.toList()));
    }

}
