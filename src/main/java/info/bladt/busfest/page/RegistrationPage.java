package info.bladt.busfest.page;

import info.bladt.busfest.component.RegistrationWizard;
import info.bladt.busfest.component.RegistrationWizardModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends BasePage {
    @SpringBean
    ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

//        CompoundPropertyModel<Visitor> visitorModel = new CompoundPropertyModel<Visitor>(new Visitor());
//        Wizard wizard = new RegistrationWizard("registrationWizard", new RegistrationWizardModel(visitorModel));
//        add(wizard);

        ConventionAttendance conventionAttendance = conventionAttendanceRepository.findOne(1L);
        add(new Label("label", new PropertyModel<String>(conventionAttendance, "visitor.lastName")));
    }
}
