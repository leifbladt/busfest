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

    public ConventionAttendance() {}

    public ConventionAttendance(Convention convention, Visitor visitor, Vehicle vehicle) {
        this.convention = convention;
        this.visitor = visitor;
        this.vehicle = vehicle;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
