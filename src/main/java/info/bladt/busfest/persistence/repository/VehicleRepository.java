package info.bladt.busfest.persistence.repository;

import info.bladt.busfest.persistence.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by leif on 09.02.14.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
