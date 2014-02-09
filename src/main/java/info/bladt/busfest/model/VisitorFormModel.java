package info.bladt.busfest.model;

import java.io.Serializable;

/**
 * Created by leif on 09.02.14.
 */
public class VisitorFormModel implements Serializable {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

