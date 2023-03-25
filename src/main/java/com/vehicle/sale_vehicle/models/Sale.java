package com.vehicle.sale_vehicle.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name= "sale")
public class Sale {

    @Id
    @Getter
    @Setter
    @Column(name = "id_sale")
    private Integer id_sale;
    @Getter
    @Setter
    @Column(name = "id_customer")
    private Integer id_customer;

    @Getter
    @Setter
    @Column(name = "id_vehicle")
    private String id_vehicle;

    @Getter
    @Setter
    @Column(name = "date_sale")
    private Date date;

    @Getter
    @Setter
    @Column(name = "value_sale")
    private Long value_sale;


}
