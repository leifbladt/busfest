package info.bladt.busfest.component;

import info.bladt.busfest.model.VisitorModel;
import info.bladt.busfest.page.DashboardPage;
import info.bladt.busfest.page.ReportPage;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.Wizard;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class RegistrationWizard extends Wizard {

    private final VisitorModel visitorModel;

    public RegistrationWizard(String id, RegistrationWizardModel registrationWizardModel) {
        super(id, registrationWizardModel, false);

        visitorModel = new VisitorModel();
        setDefaultModel(visitorModel);
    }

    @Override
    public void onCancel() {
        setResponsePage(DashboardPage.class);
    }

    @Override
    public void onFinish() {
        setResponsePage(ReportPage.class);
    }

    @Override
    protected Component newButtonBar(String id) {
//        return new Label(id, "Label");
        return super.newButtonBar(id);
    }
}
