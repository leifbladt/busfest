package info.bladt.busfest.page;

import info.bladt.busfest.component.RegistrationWizard;
import info.bladt.busfest.component.RegistrationWizardModel;
import info.bladt.busfest.model.Visitor;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends BasePage {
    @Override
    protected void onInitialize() {
        super.onInitialize();

        CompoundPropertyModel<Visitor> visitorModel = new CompoundPropertyModel<Visitor>(new Visitor());
        Wizard wizard = new RegistrationWizard("registrationWizard", new RegistrationWizardModel(visitorModel));
        add(wizard);
    }
}
