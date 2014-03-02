package info.bladt.busfest.persistence;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by leif on 09.02.14.
 */
@Table(name = "convention_attendances")
@Entity
public class ConventionAttendance extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "convention_id", nullable = false)
    private Convention convention;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "overnight_data_id")
    private OvernightData overnightData;

    @Column(name = "total_costs")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = { @Parameter(name = "currencyCode", value = "EUR") })
    private Money totalCosts;

    public ConventionAttendance() {}

    public ConventionAttendance(Convention convention, Visitor visitor, Vehicle vehicle, Money totalCosts) {
        this.convention = convention;
        this.visitor = visitor;
        this.vehicle = vehicle;
        this.totalCosts = totalCosts;
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

    public OvernightData getOvernightData() {
        return overnightData;
    }

    public void setOvernightData(OvernightData overnightData) {
        this.overnightData = overnightData;
    }

    public Money getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Money totalCosts) {
        this.totalCosts = totalCosts;
    }
}
