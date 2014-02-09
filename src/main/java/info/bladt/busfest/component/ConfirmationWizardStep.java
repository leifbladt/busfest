package info.bladt.busfest.component;

import info.bladt.busfest.persistence.Visitor;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ConfirmationWizardStep extends WizardStep {

    public ConfirmationWizardStep(CompoundPropertyModel<Visitor> visitorModel) {
        super(new ResourceModel("wizard.registration.step.confirmation.title"), Model.of(""));

        add(new Label("visitor_name", new PropertyModel(visitorModel, "firstName")));
    }
}
