package com.essawy.ecommerce.presistence.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "category", targetEntity = Item.class,
            cascade = CascadeType.ALL)
    private List<Item> items;
}
