package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class OvernightDataFormModel implements Serializable {
    private Boolean overnightVisitor;

    private Integer overnightCount;

    private Boolean caravan;

    private Integer fellowPassengerCount;

    public Boolean getOvernightVisitor() {
        return overnightVisitor;
    }

    public void setOvernightVisitor(Boolean overnightVisitor) {
        this.overnightVisitor = overnightVisitor;
    }

    public Integer getOvernightCount() {
        return overnightCount;
    }

    public void setOvernightCount(Integer overnightCount) {
        this.overnightCount = overnightCount;
    }

    public Boolean getCaravan() {
        return caravan;
    }

    public void setCaravan(Boolean caravan) {
        this.caravan = caravan;
    }

    public Integer getFellowPassengerCount() {
        return fellowPassengerCount;
    }

    public void setFellowPassengerCount(Integer fellowPassengerCount) {
        this.fellowPassengerCount = fellowPassengerCount;
    }
}
