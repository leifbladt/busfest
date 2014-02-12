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

//    // TODO Right type?
//    private Float overnightCostBus;
//
//    private Float overnightCostCaravan;
//
//    private Float dayVisitorCost;

    public Convention() {}

    public Convention(String location, int conventionNumber, Date startsOn, Date endsOn) {
        this.location = location;
        this.conventionNumber = conventionNumber;
        this.startsOn = startsOn;
        this.endsOn = endsOn;
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

//    public Float getOvernightCostBus() {
//        return overnightCostBus;
//    }
//
//    public void setOvernightCostBus(Float overnightCostBus) {
//        this.overnightCostBus = overnightCostBus;
//    }
//
//    public Float getOvernightCostCaravan() {
//        return overnightCostCaravan;
//    }
//
//    public void setOvernightCostCaravan(Float overnightCostCaravan) {
//        this.overnightCostCaravan = overnightCostCaravan;
//    }
//
//    public Float getDayVisitorCost() {
//        return dayVisitorCost;
//    }
//
//    public void setDayVisitorCost(Float dayVisitorCost) {
//        this.dayVisitorCost = dayVisitorCost;
//    }
}
