package info.bladt.busfest.persistence;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

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

    @Column(name = "convention")
    private String convention;

    @Column(name = "starts_on")
    private Date startsOn;

    @Column(name = "ends_on")
    private Date endsOn;

    @Column(name = "overnight_cost_bus")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = { @Parameter(name = "currencyCode", value = "EUR") })
    private Money overnightCostBus;

    @Column(name = "overnight_cost_caravan")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = { @Parameter(name = "currencyCode", value = "EUR") })
    private Money overnightCostCaravan;

    @Column(name = "day_visitor_cost")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = { @Parameter(name = "currencyCode", value = "EUR") })
    private Money dayVisitorCost;

    public Convention() {}

    public Convention(String location, String convention, Date startsOn, Date endsOn, Money overnightCostBus, Money overnightCostCaravan, Money dayVisitorCost) {
        this.location = location;
        this.convention = convention;
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

    public String getConvention() {
        return convention;
    }

    public void setConvention(String convention) {
        this.convention = convention;
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
        return String.format("%s %tY", getLocation(), getStartsOn());
    }

    public Money getOvernightCostBus() {
        return overnightCostBus;
    }

    public void setOvernightCostBus(Money overnightCostBus) {
        this.overnightCostBus = overnightCostBus;
    }

    public Money getOvernightCostCaravan() {
        return overnightCostCaravan;
    }

    public void setOvernightCostCaravan(Money overnightCostCaravan) {
        this.overnightCostCaravan = overnightCostCaravan;
    }

    public Money getDayVisitorCost() {
        return dayVisitorCost;
    }

    public void setDayVisitorCost(Money dayVisitorCost) {
        this.dayVisitorCost = dayVisitorCost;
    }
}
