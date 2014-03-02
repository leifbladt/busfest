package info.bladt.busfest.component;

import info.bladt.busfest.model.VisitorFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by leif on 09.02.14.
 */
public class VisitorConfirmationPanel extends Panel {

    private final IModel<? extends VisitorFormModel> model;

    public VisitorConfirmationPanel(String id, IModel<? extends VisitorFormModel> model) {
        super(id);
        this.model = model;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("addressLabel", Model.of("Anschrift")));
        add(new Label("dateOfBirthLabel", Model.of("Geburtstag")));
        add(new Label("telephoneNumberLabel", Model.of("Telefon")));
        add(new Label("emailAddressLabel", Model.of("E-Mail")));

        add(new Label("firstName", new PropertyModel(model, "firstName")));
        add(new Label("lastName", new PropertyModel(model, "lastName")));
        add(new Label("address", new PropertyModel(model, "address")));
        add(new Label("zipCode", new PropertyModel(model, "zipCode")));
        add(new Label("city", new PropertyModel(model, "city")));
        add(new Label("country", new PropertyModel(model, "country")));
        add(new Label("dateOfBirth", new PropertyModel(model, "dateOfBirth")));
        add(new Label("telephoneNumber", new PropertyModel(model, "telephoneNumber")));
        add(new Label("emailAddress", new PropertyModel(model, "emailAddress")));
    }
}
