package info.bladt.busfest.persistence.specification;

import info.bladt.busfest.persistence.Convention;
import info.bladt.busfest.persistence.ConventionAttendance;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by leif on 02.03.14.
 */
public class ConventionAttendanceSpecification {
    public static Specification<ConventionAttendance> isConvention(final Convention convention) {
        return new Specification<ConventionAttendance>() {
            @Override
            public Predicate toPredicate(Root<ConventionAttendance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("convention").get("id"), convention.getId());
            }
        };
    }

    public static Specification<ConventionAttendance> isLikeName(final String name) {
        return new Specification<ConventionAttendance>() {
            @Override
            public Predicate toPredicate(Root<ConventionAttendance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.or(
                        cb.equal(root.get("visitor").get("firstName"), name),
                        cb.equal(root.get("visitor").get("lastName"), name)
                );
            }
        };
    }
}
