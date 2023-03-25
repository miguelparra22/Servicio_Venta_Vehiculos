package com.vehicle.sale_vehicle.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name= "vehicle")
public class Vehicle {
    @Id
    @Getter @Setter @Column(name = "id_vehicle")
    private String id_vehicle;
    @Getter @Setter @Column(name = "type")
    private String type;
    @Getter @Setter @Column(name = "brand")
    private String brand;
    @Getter @Setter @Column(name = "model")
    private String model;
    @Getter @Setter @Column(name = "year")
    private Long year;
    @Getter @Setter @Column(name = "number_axes")
    private Integer number_axes;
    @Getter @Setter @Column(name = "cylinder_capacity")
    private Integer cylinder_capacity;
    @Getter @Setter @Column(name = "potency")
    private Integer potency;
    @Getter @Setter @Column(name = "value")
    private Long value;
}
