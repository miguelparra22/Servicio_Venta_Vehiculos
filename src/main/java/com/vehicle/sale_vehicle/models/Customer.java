package com.vehicle.sale_vehicle.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "customer")
public class Customer {
     @Id
     @Getter
     @Setter
     @Column(name = "id_customer")
     private Integer id_customer;
     @Getter
     @Setter
     @Column(name = "name")
     private String name;
     @Getter
     @Setter
     @Column(name = "last_name")
     private String last_name;
     @Getter
     @Setter
     @Column(name = "phone")
     private Integer phone;
     @Getter
     @Setter
     @Column(name = "address")
     private String address;
     @Getter
     @Setter
     @Column(name = "email")
     private String email;
}
