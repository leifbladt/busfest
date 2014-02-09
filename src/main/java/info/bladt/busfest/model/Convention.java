package info.bladt.busfest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class Convention implements Serializable {
    private String location;

    private int conventionNumber;

    private Date startDate;

    private Date endDate;

    // TODO Right type?
    private Float overnightCostBus;

    private Float overnightCostCaravan;

    private Float dayVisitorCost;

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

    public Float getOvernightCostBus() {
        return overnightCostBus;
    }

    public void setOvernightCostBus(Float overnightCostBus) {
        this.overnightCostBus = overnightCostBus;
    }

    public Float getOvernightCostCaravan() {
        return overnightCostCaravan;
    }

    public void setOvernightCostCaravan(Float overnightCostCaravan) {
        this.overnightCostCaravan = overnightCostCaravan;
    }

    public Float getDayVisitorCost() {
        return dayVisitorCost;
    }

    public void setDayVisitorCost(Float dayVisitorCost) {
        this.dayVisitorCost = dayVisitorCost;
    }
}
