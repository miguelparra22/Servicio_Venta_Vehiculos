package com.vehicle.sale_vehicle.dao;

import com.vehicle.sale_vehicle.models.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
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
        //resultado es una lista que se iguala a entitiyManager que es la persitencia createQuery crea el query que necesitamos y devuelve una lista
        List<Vehicle> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    public void saleVehicle(String id) {
        //Enviamos el  id como parametro y usamos el metodo find
        Vehicle vehicle = entityManager.find(Vehicle.class, id);
        //Eliminamos de la base de datos.
        entityManager.remove(vehicle);
    }

    /**
     *   Hacemos uso del entitiyManager y su metodo find para encontrar el id con el parametro seleccionado.
     *
     * **/

    @Override
    public Vehicle vehicleInfo(String id) {
        Vehicle vehicle = entityManager.find(Vehicle.class, id);
        return vehicle;
    }

    /**
     *  Con el uso de merge y el parametro del objeto guardamos la informacion que viene desde el controlador.
     * **/
    @Override
    public void registrar(Vehicle vehicle) {
       entityManager.merge(vehicle);
    }


    /***
     *    Para crear una consulta hacemos uso de CriteriBuilder, es un metodo que nos ayuda
     *    a formar las consultas que neceistamos en este caso oranizamos de menor a mayor
     *    y traemos el menor de los precios y enviamos el primer dato.
     * ***/
    @Override
    public Vehicle getVehicleChep() {
        //Declaramos el api criteriaBuilder
        CriteriaBuilder constructorConsulta = entityManager.getCriteriaBuilder();
        //Construimos el query mandandado como parametro nuestra clase
        CriteriaQuery<Vehicle> constructorQuery = constructorConsulta.createQuery(Vehicle.class);
        //Root representa la identidad raiz de la consulta es decir desde este punto crearemos la consulta y en este
        // caso seleccionamos la tabla con .from
        Root<Vehicle> vehiculoRoot = constructorQuery.from(Vehicle.class);
        //Organizamos el rsultado por el valor del vehiculo.
        constructorQuery.orderBy(constructorConsulta.desc(vehiculoRoot.get("value")));
        //Declaramos una lista del objetos y la igualamos al reusltado
        List<Vehicle> vehicles = entityManager.createQuery(constructorQuery).getResultList();
        //Enviamos el primer valor
        return vehicles.get(0);
    }

    @Override
    public Vehicle getVehicleOlder() {
        //Declaramos el api criteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Construimos el query mandandado como parametro nuestra clase
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        //Root representa la identidad raiz de la consulta es decir desde este punto crearemos la consulta y en este
        // caso seleccionamos la tabla con .from
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        //Organizamos el rsultado por el año del vehiculo.
        cq.orderBy(cb.asc(vehiculoRoot.get("year")));
        //Declaramos una lista del objetos y la igualamos al reusltado
        List<Vehicle> vehicles = entityManager.createQuery(cq).getResultList();
        //Enviamos el primer valor
        return vehicles.get(0);
    }

    @Override
    public Vehicle getVehiclePotency() {
        //Declaramos el api criteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //Construimos el query mandandado como parametro nuestra clase
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        //Root representa la identidad raiz de la consulta es decir desde este punto crearemos la consulta y en este
        // caso seleccionamos la tabla con .from
        Root<Vehicle> vehiculoRoot = cq.from(Vehicle.class);
        //Organizamos el rsultado por la potencia del vehiculo.
        cq.orderBy(cb.desc(vehiculoRoot.get("potency")));
        //Declaramos una lista del objetos y la igualamos al resultado
        List<Vehicle> vehicles = entityManager.createQuery(cq).getResultList();
        //Enviamos el primer valor
        return vehicles.get(0);
    }

    @Override
    public List<Vehicle[]>  getVehicleIdYear(Vehicle vehicle) {
        //Esperamos como respuesta un arreglo de objetos ya que criteria Builder esta utilizando multiples get.
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vehicle[]> criteriaQuery = criteriaBuilder.createQuery(Vehicle[].class);
        Root<Vehicle> root = criteriaQuery.from(Vehicle.class);

        String model = vehicle.getModel();
        Long year = vehicle.getYear();
        //Usamos predictae para declarar las condiciones que necesitamos para la consulta
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("model"), model), criteriaBuilder.equal(root.get("year"), year));

        //Especificamos el datos que queremos recibir en este caso el vehiculo y la condicion
        criteriaQuery.multiselect(root.get("id_vehicle")).where(predicate);

        List<Vehicle[]> result = entityManager.createQuery(criteriaQuery).getResultList();

        List<Vehicle[]>  id = result;
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
