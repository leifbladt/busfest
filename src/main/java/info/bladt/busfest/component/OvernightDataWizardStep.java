package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.OvernightData;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class OvernightDataWizardStep extends WizardStep {
    public OvernightDataWizardStep() {
        super(new ResourceModel("wizard.registration.step.overnight.title"), Model.of(""));

        final BootstrapForm<OvernightData> form = new BootstrapForm<OvernightData>("overnightData", Model.of(new OvernightData()));
        form.type(FormType.Horizontal);
        form.setDefaultModel(new CompoundPropertyModel(new OvernightData()));

        FormGroup caravanGroup = new FormGroup("caravanGroup", Model.of("Wohnwagen"));
        RadioGroup caravanRadioGroup = new RadioGroup("caravanRadioGroup");
        Radio radioYes = new Radio("Ja");
        Radio radioNo = new Radio("Nein");
        caravanRadioGroup.add(radioYes);
        caravanRadioGroup.add(radioNo);
        caravanGroup.add(caravanRadioGroup);
        form.add(caravanGroup);

        add(form);
    }
}
