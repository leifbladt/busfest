package info.bladt.busfest.component;

import info.bladt.busfest.model.Visitor;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class RegistrationWizardModel extends WizardModel {
    public RegistrationWizardModel(CompoundPropertyModel<Visitor> visitorModel) {
        WizardStep personalDataStep = new PersonalDataWizardStep(visitorModel);
        WizardStep vehicleStep = new VehicleWizardStep();
        WizardStep overnightStep = new OvernightDataWizardStep();
        WizardStep provisionsStep = new ProvisionsWizardStep();
        WizardStep confirmationStep = new ConfirmationWizardStep(visitorModel);
        add(personalDataStep);
        add(vehicleStep);
        add(overnightStep);
        add(provisionsStep);
        add(confirmationStep);
    }
}
