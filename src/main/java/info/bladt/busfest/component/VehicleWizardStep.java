package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.ControlGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.Vehicle;
import info.bladt.busfest.model.VehicleModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VehicleWizardStep extends WizardStep {
    public VehicleWizardStep() {
        super(new ResourceModel("wizard.registration.step.vehicle.title"), Model.of(""));

        final BootstrapForm<Vehicle> form = new BootstrapForm<Vehicle>("vehicle", new VehicleModel());
        form.type(FormType.Horizontal);
        form.setDefaultModel(new CompoundPropertyModel(new VehicleModel()));

        ControlGroup typeGroup = new ControlGroup("typeGroup", Model.of("Typ"));
        TextField type = new TextField("type");
        typeGroup.add(type);
        form.add(typeGroup);

        ControlGroup licensePlateNumberGroup = new ControlGroup("licensePlateNumberGroup", Model.of("Nummernschild"));
        TextField licensePlateNumber = new TextField("licensePlateNumber");
        licensePlateNumberGroup.add(licensePlateNumber);
        form.add(licensePlateNumberGroup);

//        ControlGroup dateOfManufactureGroup = new ControlGroup("dateOfManufactureGroup", Model.of("Baujahr"));
//        DateTextField dateOfManufacture = new DateTextField("dateOfManufacture");
//        dateOfManufactureGroup.add(dateOfManufacture);
//        form.add(dateOfManufactureGroup);

        add(form);
    }
}
