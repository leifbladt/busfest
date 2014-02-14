package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "conventions")
@Entity
public class Convention extends AbstractEntity {

    @Column(name = "location")
    private String location;

    @Column(name = "convention_number")
    private int conventionNumber;

    @Column(name = "starts_on")
    private Date startsOn;

    @Column(name = "ends_on")
    private Date endsOn;

    // TODO Switch to joda-money
    @Column(name = "overnight_cost_bus")
    private int overnightCostBus;

    @Column(name = "overnight_cost_caravan")
    private int overnightCostCaravan;

    @Column(name = "day_visitor_cost")
    private int dayVisitorCost;

    public Convention() {}

    public Convention(String location, int conventionNumber, Date startsOn, Date endsOn, int overnightCostBus, int overnightCostCaravan, int dayVisitorCost) {
        this.location = location;
        this.conventionNumber = conventionNumber;
        this.startsOn = startsOn;
        this.endsOn = endsOn;
        this.overnightCostBus = overnightCostBus;
        this.overnightCostCaravan = overnightCostCaravan;
        this.dayVisitorCost = dayVisitorCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getConventionNumber() {
        return conventionNumber;
    }

    public void setConventionNumber(int conventionNumber) {
        this.conventionNumber = conventionNumber;
    }

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public Date getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(Date endsOn) {
        this.endsOn = endsOn;
    }

    public String getDisplayName() {
        return String.format("%s 2014", getLocation());
    }

    public int getOvernightCostBus() {
        return overnightCostBus;
    }

    public void setOvernightCostBus(int overnightCostBus) {
        this.overnightCostBus = overnightCostBus;
    }

    public int getOvernightCostCaravan() {
        return overnightCostCaravan;
    }

    public void setOvernightCostCaravan(int overnightCostCaravan) {
        this.overnightCostCaravan = overnightCostCaravan;
    }

    public int getDayVisitorCost() {
        return dayVisitorCost;
    }

    public void setDayVisitorCost(int dayVisitorCost) {
        this.dayVisitorCost = dayVisitorCost;
    }
}
