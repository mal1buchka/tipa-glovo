package com.api.glovoCRM.Models.OrderDetailModels;

import com.api.glovoCRM.Models.BaseEntity;
import com.api.glovoCRM.Models.UserModels.User;
import com.api.glovoCRM.constants.EStatusOrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "oder_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail extends BaseEntity {

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private EStatusOrderDetail status;

    @OneToMany(mappedBy = "orderItem_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "orderDetail_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private PaymentDetail paymentDetail;

    @OneToOne(mappedBy = "orderDetail_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

}
