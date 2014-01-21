package info.bladt.busfest.component;

import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ProvisionsWizardStep extends WizardStep {
    public ProvisionsWizardStep() {
        super(new ResourceModel("wizard.registration.step.provisions.title"), Model.of(""));
    }

    @Override
    public boolean isComplete() {
        return true;
    }
}
