package com.vehicle.sale_vehicle.dao;

import com.vehicle.sale_vehicle.models.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
       // TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE value = (SELECT MIN(value) FROM Vehicle)", Vehicle.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        cq.orderBy(cb.desc(vehiculoRoot.get("value")));
        List<Vehicle> vehicles = entityManager.createQuery(cq).getResultList();
        return vehicles.get(0);
    }

    @Override
    public Vehicle getVehicleOlder() {
        //TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE year = (SELECT MIN(year) FROM Vehicle)", Vehicle.class);
        //return query.getResultList();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        cq.orderBy(cb.asc(vehiculoRoot.get("year")));
        List<Vehicle> vehicles = entityManager.createQuery(cq).getResultList();
        return vehicles.get(0);
    }

    @Override
    public Vehicle getVehiclePotency() {
        //TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE potency = (SELECT MAX(potency) FROM Vehicle)", Vehicle.class);
        //return query.getResultList();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        cq.orderBy(cb.asc(vehiculoRoot.get("year")));
        List<Vehicle> vehicles = entityManager.createQuery(cq).getResultList();
        return vehicles.get(0);
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

    @Override
    public List<Vehicle> orderYear() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        cq.orderBy(cb.desc(vehiculoRoot.get("year")));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Vehicle> infoVehicleCriteria2(Vehicle vehicle) {

        TypedQuery<Vehicle> query = entityManager.createQuery("FROM Vehicle WHERE year = (SELECT MIN(year) FROM Vehicle)", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public void decreasePrice(double threshold) {
        //Trae la lista
        List<Vehicle> vehicles = getVehicles();
     for (Vehicle vehicle : vehicles) {
      if (vehicle.getValue() >= threshold) {
       long newPrice = (long) (vehicle.getValue()-vehicle.getValue() * 0.10); // Reducir el precio en un 10%
           vehicle.setValue(newPrice);
           entityManager.merge(vehicle);

      }
     }
    }


}
