package info.bladt.busfest.component;

import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ConfirmationWizardStep extends WizardStep {
    public ConfirmationWizardStep() {
        super(new ResourceModel("wizard.registration.step.confirmation.title"), Model.of(""));
    }
}