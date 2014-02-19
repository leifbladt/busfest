package info.bladt.busfest.model;

import info.bladt.busfest.persistence.Visitor;

import java.io.Serializable;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class RegistrationSearchFormModel implements Serializable {

    String query;

    Visitor visitor;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
