package com.api.glovoCRM.Models.EstablishmentModels;

import com.api.glovoCRM.Models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Table(name = "products")
@Entity
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    private String image;

    private boolean active = true;

    @OneToOne(mappedBy = "product_id", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DiscountProduct discountProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Establishment establishment;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "establishment_filter_id")
    )
    private Set<Establishment_filter> establishmentFilter;
}
