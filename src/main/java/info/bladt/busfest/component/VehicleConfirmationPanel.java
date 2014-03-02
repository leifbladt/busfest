package info.bladt.busfest.component;

import info.bladt.busfest.model.VehicleFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by leif on 09.02.14.
 */
public class VehicleConfirmationPanel extends Panel {

    // TODO Add edit action

    private final IModel<? extends VehicleFormModel> model;

    public VehicleConfirmationPanel(String id, IModel<? extends VehicleFormModel> model) {
        super(id);
        this.model = model;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("typeLabel", Model.of("Typ")));
        add(new Label("licensePlateNumberLabel", Model.of("Kennzeichen")));
        add(new Label("dateOfManufactureLabel", Model.of("Baujahr")));

        add(new Label("type", new PropertyModel(model, "type")));
        add(new Label("licensePlateNumber", new PropertyModel(model, "licensePlateNumber")));
        add(new Label("dateOfManufacture", new PropertyModel(model, "dateOfManufacture")));
    }
}
