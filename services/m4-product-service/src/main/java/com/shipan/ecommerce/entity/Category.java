package com.shipan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;
    @Column(name = "description", length = 500)
    private String description;

    // removing a category ---> will remove all the related products
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE) // the "category" should be exactly as the variable name in Product
    private List<Product> products;
}
