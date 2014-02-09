package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * Created by leif on 09.02.14.
 */
public class VehicleFormModel implements Serializable {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
