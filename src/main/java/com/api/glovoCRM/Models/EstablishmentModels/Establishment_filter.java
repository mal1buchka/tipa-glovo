package com.api.glovoCRM.Models.EstablishmentModels;

import com.api.glovoCRM.Models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "establishments_filters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establishment_filter extends BaseEntity {

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    @ManyToMany(mappedBy = "establishment_filter_id")
    private Set<Product> products;
}
