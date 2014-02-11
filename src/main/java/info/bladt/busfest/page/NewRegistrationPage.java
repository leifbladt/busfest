package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.component.VehicleConfirmationPanel;
import info.bladt.busfest.component.VehicleInputPanel;
import info.bladt.busfest.component.VisitorConfirmationPanel;
import info.bladt.busfest.component.VisitorInputPanel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.Vehicle;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.persistence.repository.VehicleRepository;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration/new")
public class NewRegistrationPage extends AuthenticatedBasePage {
    @SpringBean
    private VisitorRepository visitorRepository;

    @SpringBean
    private VehicleRepository vehicleRepository;

    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final IModel<VisitorFormModel> visitorFormModel = Model.of(new VisitorFormModel());

        final VisitorForm visitorForm = new VisitorForm("visitor", visitorFormModel);
        visitorForm.setOutputMarkupId(true);
        add(visitorForm);

        final VisitorConfirmationPanel visitorConfirmation = new VisitorConfirmationPanel("visitorConfirmation", visitorFormModel);
        visitorConfirmation.setOutputMarkupPlaceholderTag(true);
        visitorConfirmation.setVisible(false);
        add(visitorConfirmation);

        final IModel<VehicleFormModel> vehicleFormModel = Model.of(new VehicleFormModel());

        final VehicleForm vehicleForm = new VehicleForm("vehicle", vehicleFormModel);
        vehicleForm.setOutputMarkupPlaceholderTag(true);
        vehicleForm.setVisible(false);
        add(vehicleForm);

        final VehicleConfirmationPanel vehicleConfirmation = new VehicleConfirmationPanel("vehicleConfirmation", vehicleFormModel);
        vehicleConfirmation.setOutputMarkupPlaceholderTag(true);
        vehicleConfirmation.setVisible(false);
        add(vehicleConfirmation);

        add(new BootstrapAjaxButton("visitorSubmit", Model.of("weiter"), visitorForm, Buttons.Type.Primary) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                VisitorFormModel modelObject = (VisitorFormModel) form.getModelObject();

                System.out.println("onSubmit!!! " + modelObject.getFirstName());
                visitorForm.setVisible(false);
                target.add(visitorForm);

                visitorConfirmation.setVisible(true);
                target.add(visitorConfirmation);

                vehicleForm.setVisible(true);
                target.add(vehicleForm);
            }
        });

        add(new BootstrapAjaxButton("vehicleSubmit", Model.of("weiter"), vehicleForm, Buttons.Type.Primary) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                VehicleFormModel modelObject = (VehicleFormModel)form.getModelObject();
                System.out.println("onSubmitAgain!!! " + modelObject.getType());

                vehicleForm.setVisible(false);
                target.add(vehicleForm);

                vehicleConfirmation.setVisible(true);
                target.add(vehicleConfirmation);
            }
        });

        add(new BootstrapAjaxLink("finalConfirmation", Model.of("anmelden"), Buttons.Type.Primary) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // TODO Extract into service object (within a transaction)
                Visitor visitor = createVisitor(visitorFormModel);
                Vehicle vehicle = createVehicle(vehicleFormModel);
                visitorRepository.save(visitor);
                vehicleRepository.save(vehicle);
                ConventionAttendance conventionAttendance = new ConventionAttendance();
                conventionAttendance.setConvention(BusfestSession.get().getActiveConvention().getObject());
                conventionAttendance.setVisitor(visitor);
                conventionAttendance.setVehicle(vehicle);
                conventionAttendanceRepository.save(conventionAttendance);

                setResponsePage(RegistrationPage.class);
            }
        });
    }

    private Visitor createVisitor(IModel<VisitorFormModel> visitorFormModel) {
        Visitor visitor = new Visitor();
        VisitorFormModel object = visitorFormModel.getObject();
        visitor.setFirstName(object.getFirstName());
        visitor.setLastName(object.getLastName());
        visitor.setStreet(object.getAddress());
        visitor.setZipCode(object.getZipCode());
        visitor.setCity(object.getCity());
        visitor.setCountry(object.getCountry());
        visitor.setDateOfBirth(object.getDateOfBirth());
        visitor.setTelephoneNumber(object.getTelephoneNumber());
        visitor.setEmailAddress(object.getEmailAddress());

        return visitor;
    }

    private Vehicle createVehicle(IModel<VehicleFormModel> vehicleFormModel) {
        Vehicle vehicle = new Vehicle();
        VehicleFormModel object = vehicleFormModel.getObject();
        vehicle.setType(object.getType());
        vehicle.setLicensePlateNumber(object.getLicensePlateNumber());

        return vehicle;
    }

    private class VisitorForm extends BootstrapForm<VisitorFormModel> {
        private final IModel<VisitorFormModel> model;

        public VisitorForm(String componentId, IModel<VisitorFormModel> model) {
            super(componentId);
            this.model = model;
            setDefaultModel(new CompoundPropertyModel(model));
            type(FormType.Horizontal);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            VisitorInputPanel visitorInputPanel = new VisitorInputPanel("visitorInput", model);
            add(visitorInputPanel);
        }
    }

    private class VehicleForm extends BootstrapForm {
        private final IModel<VehicleFormModel> model;

        public VehicleForm(String componentId, IModel<VehicleFormModel> model) {
            super(componentId);
            this.model = model;
            setDefaultModel(new CompoundPropertyModel<Object>(model));
            type(FormType.Horizontal);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            VehicleInputPanel vehicleInputPanel = new VehicleInputPanel("vehicleInput", model);
            add(vehicleInputPanel);
        }
    }
}
