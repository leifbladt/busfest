package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * Created by leif on 09.02.14.
 */
public class VehicleFormModel implements Serializable {
    private String type;
    private String licensePlateNumber;

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
}
