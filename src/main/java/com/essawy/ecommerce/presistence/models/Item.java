package com.essawy.ecommerce.presistence.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = false, length = 90)
    private String description;
    @Column(name = "image", nullable = false)
    private String imageUrl;
    @Column(name = "price", nullable = false, length = 5)
    private BigDecimal price;
    private boolean active;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;
}
