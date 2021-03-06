package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.OvernightDataFormModel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by leif on 11.02.14.
 */
public class OvernightDataInputPanel extends Panel {
    public OvernightDataInputPanel(String id, IModel<OvernightDataFormModel> model) {
        super(id, model);
        IModel<OvernightDataFormModel> compound = new CompoundPropertyModel<>(model);

        final BootstrapForm<OvernightDataFormModel> form = new BootstrapForm<>("overnightData", compound);
        form.add(new FormBehavior(FormType.Horizontal));

        FormGroup overnightVisitorGroup = new CustomFormGroup("overnightVisitorGroup", Model.of("Übernachtung"));
        CheckBox overnightVisitor = new CheckBox("overnightVisitor");
        overnightVisitorGroup.add(overnightVisitor);
        form.add(overnightVisitorGroup);

        FormGroup overnightCountGroup = new CustomFormGroup("overnightCountGroup", Model.of("Anzahl Übernachtungen"));
        NumberTextField overnightCount = new NumberTextField("overnightCount");
        overnightCountGroup.add(overnightCount);
        form.add(overnightCountGroup);

        FormGroup caravanGroup = new CustomFormGroup("caravanGroup", Model.of("Wohnwagen"));
        CheckBox caravan = new CheckBox("caravan");
        caravanGroup.add(caravan);
        form.add(caravanGroup);

        FormGroup fellowPassengerCountGroup = new CustomFormGroup("fellowPassengersGroup", Model.of("Anzahl Mitreisende"));
        NumberTextField fellowPassengerCount = new NumberTextField("fellowPassengers");
        fellowPassengerCountGroup.add(fellowPassengerCount);
        form.add(fellowPassengerCountGroup);

        add(form);
    }
}
