package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.OvernightDataFormModel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by leif on 11.02.14.
 */
public class OvernightDataInputPanel extends Panel {
    public OvernightDataInputPanel(String id, IModel<OvernightDataFormModel> model) {
        super(id, model);
        IModel<OvernightDataFormModel> compound = new CompoundPropertyModel<OvernightDataFormModel>(model);

        final BootstrapForm<OvernightDataFormModel> form = new BootstrapForm<OvernightDataFormModel>("overnightData", compound);
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
        RadioGroup caravanRadioGroup = new RadioGroup("caravan");
        Radio radioYes = new Radio("Ja", new PropertyModel(model, "caravan"));
        Radio radioNo = new Radio("Nein", new PropertyModel(model, "caravan"));
        caravanRadioGroup.add(radioYes);
        caravanRadioGroup.add(radioNo);
        caravanGroup.add(caravanRadioGroup);
        form.add(caravanGroup);

        FormGroup fellowPassengerCountGroup = new CustomFormGroup("fellowPassengersGroup", Model.of("Anzahl Mitreisende"));
        NumberTextField fellowPassengerCount = new NumberTextField("fellowPassengers");
        fellowPassengerCountGroup.add(fellowPassengerCount);
        form.add(fellowPassengerCountGroup);

        add(form);
    }
}
