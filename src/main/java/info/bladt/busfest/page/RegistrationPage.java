package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.ControlGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import info.bladt.busfest.model.Visitor;
import info.bladt.busfest.model.VisitorModel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends BasePage {
    @Override
    protected void onInitialize() {
        super.onInitialize();

        final BootstrapForm<Visitor> form = new BootstrapForm<Visitor>("visitor", new VisitorModel());
        form.type(FormType.Horizontal);
        form.setDefaultModel(new CompoundPropertyModel(new VisitorModel()));

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

    @Override
    protected String pageTitle() {
        return "Anmeldung";
    }
}
