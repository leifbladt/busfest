package info.bladt.busfest.persistence.repository;

import info.bladt.busfest.persistence.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    // TODO Make use of specifications
    // TODO DISTINCT
    public List<Visitor> findByFirstNameLikeOrLastNameLikeAllIgnoreCase(String firstName, String lastName);
}
