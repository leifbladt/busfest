package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.ProvisionFormModel;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by leif on 11.02.14.
 */
public class ProvisionInputPanel extends Panel {
    public ProvisionInputPanel(String id, IModel<ProvisionFormModel> model) {
        super(id, model);
        IModel<ProvisionFormModel> compound = new CompoundPropertyModel<>(model);

        final BootstrapForm<ProvisionFormModel> form = new BootstrapForm<>("overnightData", compound);
        form.add(new FormBehavior(FormType.Horizontal));

        FormGroup p1CountSatGroup = new CustomFormGroup("p1CountSatGroup", Model.of("Roggenweck (Samstag)"));
        NumberTextField p1CountSat = new NumberTextField("p1CountSat");
        p1CountSatGroup.add(p1CountSat);
        form.add(p1CountSatGroup);

        FormGroup p2CountSatGroup = new CustomFormGroup("p2CountSatGroup", Model.of("Kaiser-/Spitzweck"));
        NumberTextField p2CountSat = new NumberTextField("p2CountSat");
        p2CountSatGroup.add(p2CountSat);
        form.add(p2CountSatGroup);

        FormGroup p3CountSatGroup = new CustomFormGroup("p3CountSatGroup", Model.of("Bretzel"));
        NumberTextField p3CountSat = new NumberTextField("p3CountSat");
        p3CountSatGroup.add(p3CountSat);
        form.add(p3CountSatGroup);

        FormGroup p1CountSunGroup = new CustomFormGroup("p1CountSunGroup", Model.of("Roggenweck (Sonntag)"));
        NumberTextField p1CountSun = new NumberTextField("p1CountSun");
        p1CountSunGroup.add(p1CountSun);
        form.add(p1CountSunGroup);

        FormGroup p2CountSunGroup = new CustomFormGroup("p2CountSunGroup", Model.of("Kaiser-/Spitzweck"));
        NumberTextField p2CountSun = new NumberTextField("p2CountSun");
        p2CountSunGroup.add(p2CountSun);
        form.add(p2CountSunGroup);

        FormGroup p3CountSunGroup = new CustomFormGroup("p3CountSunGroup", Model.of("Bretzel"));
        NumberTextField p3CountSun = new NumberTextField("p3CountSun");
        p3CountSunGroup.add(p3CountSun);
        form.add(p3CountSunGroup);

        add(form);
    }
}
