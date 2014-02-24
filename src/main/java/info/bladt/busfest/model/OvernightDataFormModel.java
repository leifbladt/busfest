package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class OvernightDataFormModel implements Serializable {

    private Boolean overnightVisitor = true;

    private int overnightCount;

    private Boolean caravan;

    private int fellowPassengers;

    public Boolean getOvernightVisitor() {
        return overnightVisitor;
    }

    public void setOvernightVisitor(Boolean overnightVisitor) {
        this.overnightVisitor = overnightVisitor;
    }

    public int getOvernightCount() {
        return overnightCount;
    }

    public void setOvernightCount(int overnightCount) {
        this.overnightCount = overnightCount;
    }

    public Boolean getCaravan() {
        return caravan;
    }

    public void setCaravan(Boolean caravan) {
        this.caravan = caravan;
    }

    public int getFellowPassengers() {
        return fellowPassengers;
    }

    public void setFellowPassengers(int fellowPassengers) {
        this.fellowPassengers = fellowPassengers;
    }
}
