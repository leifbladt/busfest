package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "vehicles")
@Entity
public class Vehicle extends AbstractEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @Column(name = "month_of_manufacture")
    private Integer monthOfManufacture;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    public Vehicle() {}

    public Vehicle(String type, String licensePlateNumber, int monthOfManufacture, int yearOfManufacture) {
        this.type = type;
        this.licensePlateNumber = licensePlateNumber;
        this.monthOfManufacture = monthOfManufacture;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Integer getMonthOfManufacture() {
        return monthOfManufacture;
    }

    public void setMonthOfManufacture(Integer monthOfManufacture) {
        this.monthOfManufacture = monthOfManufacture;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
