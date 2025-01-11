package com.api.glovoCRM.Models.EstablishmentModels;

import com.api.glovoCRM.Models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "caterories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    private String name;

    private String image;

    @OneToMany(mappedBy = "category_id", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SubCategory> subCategory;
}
