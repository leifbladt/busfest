package info.bladt.busfest.component;

import info.bladt.busfest.page.DashboardPage;
import info.bladt.busfest.page.ReportPage;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class RegistrationWizard extends Wizard {

    public RegistrationWizard(String id) {
        super(id, new RegistrationWizardModel());
    }

    @Override
    public void onCancel() {
        setResponsePage(DashboardPage.class);
    }

    @Override
    public void onFinish() {
        setResponsePage(ReportPage.class);
    }

    private static class RegistrationWizardModel extends WizardModel {
        private RegistrationWizardModel() {
            WizardStep personalDataStep = new PersonalDataWizardStep();
            WizardStep vehicleStep = new VehicleWizardStep();
            WizardStep overnightStep = new OvernightDataWizardStep();
            WizardStep provisionsStep = new ProvisionsWizardStep();
            WizardStep confirmationStep = new ConfirmationWizardStep();
            add(personalDataStep);
            add(vehicleStep);
            add(overnightStep);
            add(provisionsStep);
            add(confirmationStep);
        }
    }
}
