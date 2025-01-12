package com.api.glovoCRM.Models.EstablishmentModels;

import com.api.glovoCRM.Models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "establishments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establishment extends BaseEntity {

    private String name;

    private double priceOfDelivery;

    private int timeOdDelivery;

    private String image;

    private int rating;

    private int quantityOfRatings;


    private boolean isOpen; //вопрос ребятам как это потом реализовать

    @OneToMany(mappedBy = "establishment_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> product;

    @OneToMany(mappedBy = "establishment_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Establishment_filter> establishment_filter;
}
