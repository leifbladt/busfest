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
    // TODO Only month and year of manufacture?

    @Column(name = "type")
    private String type;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @Column(name = "date_of_manufacture")
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
