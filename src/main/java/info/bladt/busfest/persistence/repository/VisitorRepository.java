package info.bladt.busfest.persistence.repository;

import info.bladt.busfest.persistence.Visitor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public interface VisitorRepository extends CrudRepository<Visitor, Long> {
}
