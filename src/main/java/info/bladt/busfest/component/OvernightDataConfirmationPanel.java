package info.bladt.busfest.component;

import info.bladt.busfest.model.OvernightDataFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by leif on 09.02.14.
 */
public class OvernightDataConfirmationPanel extends Panel {

    // TODO Add edit action

    private final IModel<? extends OvernightDataFormModel> model;

    public OvernightDataConfirmationPanel(String id, IModel<? extends OvernightDataFormModel> model) {
        super(id);
        this.model = model;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("overnightCountLabel", Model.of("Übernachtungen")));
        add(new Label("fellowPassengerCountLabel", Model.of("Mitreisende")));

        add(new Label("overnightCount", new PropertyModel(model, "overnightCount")));
        add(new Label("fellowPassengerCount", new PropertyModel(model, "fellowPassengerCount")));
    }
}
