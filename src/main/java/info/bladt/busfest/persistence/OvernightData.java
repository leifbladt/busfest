package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "overnight_data")
@Entity
public class OvernightData extends AbstractEntity {

    @Column(name = "fellow_passengers")
    private int fellowPassengers;

    public OvernightData() {}

    public OvernightData(int fellowPassengers) {
        this.fellowPassengers = fellowPassengers;
    }

    public int getFellowPassengers() {
        return fellowPassengers;
    }

    public void setFellowPassengers(int fellowPassengers) {
        this.fellowPassengers = fellowPassengers;
    }
}
