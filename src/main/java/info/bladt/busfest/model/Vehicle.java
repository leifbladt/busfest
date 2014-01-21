package info.bladt.busfest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class Vehicle implements Serializable {

    private String type;

    private String licensePlateNumber;

    private Date dateOfManufacture;

    public Vehicle() {}

    public Vehicle(String type, String licensePlateNumber, Date yearOfManufacture) {
        this.type = type;
        this.licensePlateNumber = licensePlateNumber;
        this.dateOfManufacture = yearOfManufacture;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date yearOfManufacture) {
        this.dateOfManufacture = yearOfManufacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
