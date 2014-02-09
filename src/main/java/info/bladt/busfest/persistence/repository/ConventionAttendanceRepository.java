package info.bladt.busfest.persistence.repository;

import info.bladt.busfest.persistence.ConventionAttendance;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by leif on 09.02.14.
 */
public interface ConventionAttendanceRepository extends CrudRepository<ConventionAttendance, Long> {
}
