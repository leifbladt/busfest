package info.bladt.busfest.persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by leif on 09.02.14.
 */
@Table(name = "convention_attendances")
@Entity
public class ConventionAttendance implements Serializable {
    // TODO Add created_at

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "convention_id", nullable = false)
    private Convention convention;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
