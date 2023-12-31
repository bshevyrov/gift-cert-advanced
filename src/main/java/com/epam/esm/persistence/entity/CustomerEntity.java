package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * Entity that represent customer table.
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class CustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "customer-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer-generator", sequenceName = "customer_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;
}




