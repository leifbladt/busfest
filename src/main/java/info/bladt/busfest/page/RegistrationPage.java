package info.bladt.busfest.page;

import info.bladt.busfest.component.RegistrationWizard;
import org.apache.wicket.extensions.wizard.Wizard;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends BasePage {
    @Override
    protected void onInitialize() {
        super.onInitialize();

        Wizard wizard = new RegistrationWizard("registrationWizard");
        add(wizard);
    }

    @Override
    protected String pageTitle() {
        return "Anmeldung";
    }
}
