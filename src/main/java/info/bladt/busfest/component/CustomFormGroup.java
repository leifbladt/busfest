package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class CustomFormGroup extends FormGroup {

    public CustomFormGroup(String id, IModel<String> label) {
        super(id, label);
    }

    @Override
    protected Component newLabel(String id, IModel<String> model) {
        // TODO Find a better way to style label in a form group
        Component label = super.newLabel(id, model);
        label.add(new AttributeAppender("class", new Model("col-md-3"), " "));
        return label;
    }
}
