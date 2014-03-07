package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ProvisionFormModel implements Serializable {

    // TODO Generalize!

    private int p1CountSat;

    private int p2CountSat;

    private int p3CountSat;

    private int p1CountSun;

    private int p2CountSun;

    private int p3CountSun;

    public int getP1CountSat() {
        return p1CountSat;
    }

    public void setP1CountSat(int p1CountSat) {
        this.p1CountSat = p1CountSat;
    }

    public int getP2CountSat() {
        return p2CountSat;
    }

    public void setP2CountSat(int p2CountSat) {
        this.p2CountSat = p2CountSat;
    }

    public int getP3CountSat() {
        return p3CountSat;
    }

    public void setP3CountSat(int p3CountSat) {
        this.p3CountSat = p3CountSat;
    }

    public int getP1CountSun() {
        return p1CountSun;
    }

    public void setP1CountSun(int p1CountSun) {
        this.p1CountSun = p1CountSun;
    }

    public int getP2CountSun() {
        return p2CountSun;
    }

    public void setP2CountSun(int p2CountSun) {
        this.p2CountSun = p2CountSun;
    }

    public int getP3CountSun() {
        return p3CountSun;
    }

    public void setP3CountSun(int p3CountSun) {
        this.p3CountSun = p3CountSun;
    }
}
