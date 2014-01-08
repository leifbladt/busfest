package info.bladt.busfest.page;

import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/payoff")
public class PayoffPage extends BasePage {
    @Override
    protected String pageTitle() {
        return "Abrechnung";
    }
}
