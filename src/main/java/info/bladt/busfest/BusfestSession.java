package info.bladt.busfest;

import info.bladt.busfest.model.Convention;
import org.apache.wicket.Session;
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

    public Convention getActiveConvention() {
        if (activeConvention == null) {
            activeConvention = new Convention("Kirchzarten", 11, null, null);
        }
        return activeConvention;
    }
}
