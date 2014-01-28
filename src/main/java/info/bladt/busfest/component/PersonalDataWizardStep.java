package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.ControlGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import info.bladt.busfest.model.Visitor;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class PersonalDataWizardStep extends WizardStep {
    public PersonalDataWizardStep(CompoundPropertyModel<Visitor> visitorModel) {
        super(new ResourceModel("wizard.registration.step.visitor.title"), Model.of(""));

        final BootstrapForm<Visitor> form = new BootstrapForm<Visitor>("visitor", visitorModel);
        form.type(FormType.Horizontal);
        form.setDefaultModel(visitorModel);

        ControlGroup nameGroup = new ControlGroup("nameGroup", Model.of("Vorname / Nachname"));
        TextField firstName = new TextField("firstName");
        nameGroup.add(firstName);
        TextField lastName = new TextField("lastName");
        nameGroup.add(lastName);
        form.add(nameGroup);

        ControlGroup addressGroup = new ControlGroup("addressGroup", Model.of("Strasse"));
        TextField address = new TextField("address");
        addressGroup.add(address);
        form.add(addressGroup);

        ControlGroup cityGroup = new ControlGroup("cityGroup", Model.of("PLZ / Ort"));
        TextField zipCode = new TextField("zipCode");
        TextField city = new TextField("city");
        cityGroup.add(zipCode);
        cityGroup.add(city);
        form.add(cityGroup);

        ControlGroup countryGroup = new ControlGroup("countryGroup", Model.of("Land"));
        TextField country = new TextField("country");
        countryGroup.add(country);
        form.add(countryGroup);

        ControlGroup dateOfBirthGroup = new ControlGroup("dateOfBirthGroup", Model.of("Geburtstag"));
        DateTextField dateOfBirth = new DateTextField("dateOfBirth");
        dateOfBirthGroup.add(dateOfBirth);
        form.add(dateOfBirthGroup);

        add(form);
    }
}
