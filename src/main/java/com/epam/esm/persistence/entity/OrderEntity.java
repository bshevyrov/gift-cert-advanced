package com.epam.esm.persistence.entity;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.GiftCertificateUpdateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "\"order\"")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends AbstractAuditEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "order-generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order-generator",sequenceName= "order_sequence",allocationSize = 10,initialValue = 50)
    @Null
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @Positive(message = "Internal error with field cost.")
    private double cost;
}
