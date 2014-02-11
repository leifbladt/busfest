package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import info.bladt.busfest.model.VisitorFormModel;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VisitorInputPanel extends Panel {

    public VisitorInputPanel(String id, IModel<VisitorFormModel> model) {
        super(id, model);
        IModel<VisitorFormModel> compound = new CompoundPropertyModel<VisitorFormModel>(model);

        final BootstrapForm<VisitorFormModel> form = new BootstrapForm<VisitorFormModel>("visitor", compound);
        form.type(FormType.Horizontal);

        FormGroup nameGroup = new FormGroup("nameGroup", Model.of("Vorname / Nachname"));
        TextField firstName = new TextField("firstName");
        nameGroup.add(firstName);
        TextField lastName = new TextField("lastName");
        nameGroup.add(lastName);
        form.add(nameGroup);

        FormGroup addressGroup = new FormGroup("addressGroup", Model.of("Strasse"));
        TextField address = new TextField("address");
        addressGroup.add(address);
        form.add(addressGroup);

        FormGroup cityGroup = new FormGroup("cityGroup", Model.of("PLZ / Ort"));
        TextField zipCode = new TextField("zipCode");
        TextField city = new TextField("city");
        cityGroup.add(zipCode);
        cityGroup.add(city);
        form.add(cityGroup);

        FormGroup countryGroup = new FormGroup("countryGroup", Model.of("Land"));
        TextField country = new TextField("country");
        countryGroup.add(country);
        form.add(countryGroup);

        FormGroup dateOfBirthGroup = new FormGroup("dateOfBirthGroup", Model.of("Geburtstag"));
        DateTextField dateOfBirth = new DateTextField("dateOfBirth");
        dateOfBirthGroup.add(dateOfBirth);
        form.add(dateOfBirthGroup);

        FormGroup telephoneNumberGroup = new FormGroup("telephoneNumberGroup", Model.of("Telefon"));
        TextField telephoneNumber = new TextField("telephoneNumber");
        telephoneNumberGroup.add(telephoneNumber);
        form.add(telephoneNumberGroup);

        FormGroup emailAddressGroup = new FormGroup("emailAddressGroup", Model.of("E-Mail"));
        EmailTextField emailAddress = new EmailTextField("emailAddress");
        emailAddressGroup.add(emailAddress);
        form.add(emailAddressGroup);

        add(form);
    }
}
