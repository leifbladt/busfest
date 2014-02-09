package info.bladt.busfest.component;

import info.bladt.busfest.model.VisitorFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
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

        add(new Label("label", new PropertyModel(model, "firstName")));
    }
}
