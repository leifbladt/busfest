package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * Created by leif on 09.02.14.
 */
public class VehicleFormModel implements Serializable {
    private String type;
    private String licensePlateNumber;
    private Integer monthOfManufacture;
    private Integer yearOfManufacture;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDateOfManufacture() {
        if (getMonthOfManufacture() != null && getYearOfManufacture() != null) {
            return String.format("%s/%s", getMonthOfManufacture(), getYearOfManufacture());
        } else if (getYearOfManufacture() != null) {
            return getYearOfManufacture().toString();
        } else {
            return "";
        }
    }
}
