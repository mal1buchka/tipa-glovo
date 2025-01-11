package com.api.glovoCRM.Models.UserModels;

import com.api.glovoCRM.constants.ERoles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

}
