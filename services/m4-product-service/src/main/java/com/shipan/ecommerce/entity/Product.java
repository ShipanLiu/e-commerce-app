package com.shipan.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // the category data is only loaded from the database when it’s actually accessed in the code. This is a common best practice because it minimizes the number of database calls, especially when the associated data is not always needed.
    @JoinColumn(name = "category_id", nullable = false)   // When you retrieve a Product object from the database, the Category object (referenced by category) is not fetched immediately. Instead, a proxy for Category is returned. The actual data for Category is only fetched when a getter method (e.g., getCategory()) is called
    private Category category;

}
