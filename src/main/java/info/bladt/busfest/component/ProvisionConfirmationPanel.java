package info.bladt.busfest.component;

import info.bladt.busfest.model.ProvisionFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by leif on 09.02.14.
 */
public class ProvisionConfirmationPanel extends Panel {

    private final IModel<? extends ProvisionFormModel> model;

    public ProvisionConfirmationPanel(String id, IModel<? extends ProvisionFormModel> model) {
        super(id);
        this.model = model;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("p1CountSatLabel", Model.of("Roggenweck")));
        add(new Label("p2CountSatLabel", Model.of("Kaiser-/Spitzweck")));
        add(new Label("p3CountSatLabel", Model.of("Bretzel")));

        add(new Label("p1CountSunLabel", Model.of("Roggenweck")));
        add(new Label("p2CountSunLabel", Model.of("Kaiser-/Spitzweck")));
        add(new Label("p3CountSunLabel", Model.of("Bretzel")));

        add(new Label("p1CountSat", new PropertyModel(model, "p1CountSat")));
        add(new Label("p2CountSat", new PropertyModel(model, "p2CountSat")));
        add(new Label("p3CountSat", new PropertyModel(model, "p3CountSat")));

        add(new Label("p1CountSun", new PropertyModel(model, "p1CountSun")));
        add(new Label("p2CountSun", new PropertyModel(model, "p2CountSun")));
        add(new Label("p3CountSun", new PropertyModel(model, "p3CountSun")));
    }
}
