package info.bladt.busfest.persistence.repository;

import info.bladt.busfest.persistence.Provision;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public interface ProvisionRepository extends CrudRepository<Provision, Long> {
}
