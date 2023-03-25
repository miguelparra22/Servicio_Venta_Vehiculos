package com.vehicle.sale_vehicle.controllers;


import com.vehicle.sale_vehicle.dao.VehicleDao;
import com.vehicle.sale_vehicle.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    //Traemos el dao para hacer uso de sus funciones
    @Autowired
    private VehicleDao vehicleDao;

    @RequestMapping(value = "vehicles")
    public List<Vehicle> getVehicles(){
        return vehicleDao.getVehicles();
    }

    @RequestMapping(value = "vehicles/sale/{id}", method = RequestMethod.DELETE)
    public String ventaVehiculo(@PathVariable String id){
          vehicleDao.saleVehicle(id);
          return "Venta exitosa.";
    }


    @RequestMapping(value = "vehicles/info/{id}", method = RequestMethod.GET)
    public Vehicle infoVehiculo(@PathVariable String id){
        return vehicleDao.vehicleInfo(id);
    }

    @RequestMapping(value = "vehicles/add", method = RequestMethod.POST)
    public void registrarVehiculo(@RequestBody Vehicle vehicle){
        vehicleDao.registrar(vehicle);
    }


    @RequestMapping(value = "vehicles/info/vehicleChipper", method = RequestMethod.GET)
    public Vehicle infoMostShipperVehicle(Vehicle vehicle){
        return  vehicleDao.getVehicleShipper();
    }


    @RequestMapping(value = "vehicles/info/vehicleOlder", method = RequestMethod.GET)
    public List<Vehicle> infoVehicleOlder(Vehicle vehicle){
        return vehicleDao.getVehicleOlder();
    }

    @RequestMapping(value = "vehicles/info/vehicleMostPotency", method = RequestMethod.GET)
    public List<Vehicle> infoVehicleOPotency(Vehicle vehicle){
        return vehicleDao.getVehiclePotency();
    }

    @RequestMapping(value = "vehicles/infoModelYear")
    public List<Vehicle> infoVehicle(@RequestBody Vehicle vehicle){

        return vehicleDao.getVehicleIdYear(vehicle);
    }


}
