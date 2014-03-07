package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "convention_attendance_provisions")
@Entity
public class ConventionAttendanceProvision extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "convention_attendance_id")
    private ConventionAttendance conventionAttendance;

    @ManyToOne
    @JoinColumn(name = "provision_id")
    private Provision provision;

    @Column(name = "delivered_on")
    private Date deliveredOn;

    @Column(name = "count")
    private int count;

    public ConventionAttendanceProvision() {
    }

    public ConventionAttendanceProvision(ConventionAttendance conventionAttendance, Provision provision) {
        this.conventionAttendance = conventionAttendance;
        this.provision = provision;
    }

    public ConventionAttendance getConventionAttendance() {
        return conventionAttendance;
    }

    public void setConventionAttendance(ConventionAttendance conventionAttendance) {
        this.conventionAttendance = conventionAttendance;
    }

    public Provision getProvision() {
        return provision;
    }

    public void setProvision(Provision provision) {
        this.provision = provision;
    }

    public Date getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
