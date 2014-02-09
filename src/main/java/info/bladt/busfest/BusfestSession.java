package info.bladt.busfest;

import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.Convention;
import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class BusfestSession extends WebSession {
    private Convention activeConvention;

    public BusfestSession(Request request) {
        super(request);
    }

    public static BusfestSession get() {
        return (BusfestSession) Session.get();
    }

    public ConventionModel getActiveConvention() {
        // TODO Determine active convention (per Session/User/Global)
        return new ConventionModel(7L);
    }
}
