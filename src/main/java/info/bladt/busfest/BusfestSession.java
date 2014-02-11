package info.bladt.busfest;

import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.Convention;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class BusfestSession extends AuthenticatedWebSession {
    private Convention activeConvention;

    public BusfestSession(Request request) {
        super(request);
    }

    @Override
    public Roles getRoles() {
        return isSignedIn() ? new Roles("ADMIN") : null;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // TODO Create user model in database
        return "vwbus".equals(username) && "team11".equals(password);
    }

    public static BusfestSession get() {
        return (BusfestSession) Session.get();
    }

    public ConventionModel getActiveConvention() {
        // TODO Determine active convention (per Session/User/Global)
        return new ConventionModel(7L);
    }
}
