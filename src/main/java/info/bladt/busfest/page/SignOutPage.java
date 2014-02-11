package info.bladt.busfest.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("signout")
public class SignOutPage extends BasePage {
    public SignOutPage() {
        this(null);
    }

    public SignOutPage(PageParameters parameters) {
        super(parameters);
        getSession().invalidate();
        setResponsePage(DashboardPage.class);
    }
}
