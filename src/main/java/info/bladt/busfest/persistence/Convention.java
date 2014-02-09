package info.bladt.busfest.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "conventions")
@Entity
public class Convention implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "convention_number")
    private int conventionNumber;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

//    // TODO Right type?
//    private Float overnightCostBus;
//
//    private Float overnightCostCaravan;
//
//    private Float dayVisitorCost;

    public Convention() {}

    public Convention(String location, int conventionNumber, Date startDate, Date endDate) {
        this.location = location;
        this.conventionNumber = conventionNumber;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
