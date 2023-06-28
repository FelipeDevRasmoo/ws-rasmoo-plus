package com.client.ws.rasmooplus.model.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscriptions_type")
@Entity
public class SubscriptionType extends RepresentationModel<SubscriptionType> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long id;

    private String name;

    @Column(name = "access_months")
    private Long accessMonths;

    private BigDecimal price;

    @Column(name = "product_key",unique = true)
    private String productKey;


}
