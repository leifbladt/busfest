package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.ConfirmationFormModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ConfirmationInputPanel extends Panel {
    public ConfirmationInputPanel(String id, IModel<ConfirmationFormModel> model) {
        super(id, model);
        IModel<ConfirmationFormModel> compound = new CompoundPropertyModel<>(model);

        final BootstrapForm<ConfirmationFormModel> form = new BootstrapForm<>("confirmation", compound);
        form.add(new FormBehavior(FormType.Horizontal));

        FormGroup totalCostsGroup = new CustomFormGroup("totalCostsGroup", Model.of("Gesamtkosten"));
        Label totalCosts = new Label("totalCosts");
        totalCostsGroup.add(totalCosts);
        form.add(totalCostsGroup);

        add(form);
    }
}
