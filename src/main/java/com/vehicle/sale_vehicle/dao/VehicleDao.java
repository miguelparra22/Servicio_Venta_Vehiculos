package com.vehicle.sale_vehicle.dao;

import com.vehicle.sale_vehicle.models.Vehicle;

import java.util.List;

public interface VehicleDao {
    List<Vehicle> getVehicles();

    void saleVehicle(String id);

    Vehicle vehicleInfo(String id);

    void registrar(Vehicle vehicle);

    Vehicle getVehicleShipper();

    List<Vehicle> getVehicleOlder();

    List<Vehicle> getVehiclePotency();

    List<Vehicle> getVehicleIdYear(Vehicle vehicle);
}
