package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.VehicleFormModel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by leif on 09.02.14.
 */
public class VehicleInputPanel extends Panel {
    public VehicleInputPanel(String id, IModel<VehicleFormModel> model) {
        super(id, model);
        IModel<VehicleFormModel> compound = new CompoundPropertyModel<>(model);

        final BootstrapForm<VehicleFormModel> form = new BootstrapForm<>("vehicle", compound);
        form.add(new FormBehavior(FormType.Horizontal));

        FormGroup typeGroup = new CustomFormGroup("typeGroup", Model.of("Typ"));
        TextField type = new TextField("type");
        typeGroup.add(type);
        form.add(typeGroup);

        FormGroup licensePlateNumberGroup = new CustomFormGroup("licensePlateNumberGroup", Model.of("Nummernschild"));
        TextField licensePlateNumber = new TextField("licensePlateNumber");
        licensePlateNumberGroup.add(licensePlateNumber);
        form.add(licensePlateNumberGroup);

        FormGroup dateOfManufactureGroup = new CustomFormGroup("dateOfManufactureGroup", Model.of("Baujahr"));
        TextField monthOfManufacture = new TextField("monthOfManufacture");
        TextField yearOfManufacture = new TextField("yearOfManufacture");
        dateOfManufactureGroup.add(monthOfManufacture);
        dateOfManufactureGroup.add(yearOfManufacture);
        form.add(dateOfManufactureGroup);

        add(form);
    }
}
