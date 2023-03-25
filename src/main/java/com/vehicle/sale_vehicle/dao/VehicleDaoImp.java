package com.vehicle.sale_vehicle.dao;

import com.vehicle.sale_vehicle.models.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class VehicleDaoImp  implements  VehicleDao{

    //Conexion a base de datos
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vehicle> getVehicles() {
        //Hace select a la clase
        String query = "From Vehicle";
        List<Vehicle> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    public void saleVehicle(String id) {
        Vehicle vehicle = entityManager.find(Vehicle.class, id);
        entityManager.remove(vehicle);
    }

    @Override
    public Vehicle vehicleInfo(String id) {
        Vehicle vehicle = entityManager.find(Vehicle.class, id);
        return vehicle;
    }

    @Override
    public void registrar(Vehicle vehicle) {
       entityManager.merge(vehicle);
    }

    @Override
    public Vehicle getVehicleShipper() {
        TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE value = (SELECT MIN(value) FROM Vehicle)", Vehicle.class);
        return query.getSingleResult();
    }

    @Override
    public List<Vehicle> getVehicleOlder() {
        TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE year = (SELECT MIN(year) FROM Vehicle)", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public List<Vehicle> getVehiclePotency() {
        TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE potency = (SELECT MAX(potency) FROM Vehicle)", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public List<Vehicle>  getVehicleIdYear(Vehicle vehicle) {

        //TypedQuery<Vehicle> query = entityManager.createQuery("SELECT id_vehicle From Vehicle WHERE model = '"+ model +"'" , Vehicle.class);
        String query = "SELECT id_vehicle FROM Vehicle WHERE model= :model AND year = :year";
        List<Vehicle> result = entityManager.createQuery(query)
                .setParameter("model", vehicle.getModel())
                .setParameter("year", vehicle.getYear())
                .getResultList();


        List<Vehicle>  id = result;
        return id;

    }


}
