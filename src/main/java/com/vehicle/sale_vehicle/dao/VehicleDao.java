package com.vehicle.sale_vehicle.dao;

import com.vehicle.sale_vehicle.models.Vehicle;

import java.util.List;

public interface VehicleDao {
    List<Vehicle> getVehicles();

    void saleVehicle(String id);

    Vehicle vehicleInfo(String id);

    void registrar(Vehicle vehicle);

    Vehicle getVehicleShipper();

    Vehicle getVehicleOlder();

    Vehicle getVehiclePotency();

    List<Vehicle> getVehicleIdYear(Vehicle vehicle);

    List<Vehicle> orderYear();


    List<Vehicle> infoVehicleCriteria2(Vehicle vehicle);


    void decreasePrice(double threshold);
}
