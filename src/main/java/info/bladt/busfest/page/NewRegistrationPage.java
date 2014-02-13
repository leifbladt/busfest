package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.component.OvernightDataConfirmationPanel;
import info.bladt.busfest.component.OvernightDataInputPanel;
import info.bladt.busfest.component.VehicleConfirmationPanel;
import info.bladt.busfest.component.VehicleInputPanel;
import info.bladt.busfest.component.VisitorConfirmationPanel;
import info.bladt.busfest.component.VisitorInputPanel;
import info.bladt.busfest.model.OvernightDataFormModel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.OvernightData;
import info.bladt.busfest.persistence.Vehicle;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.persistence.repository.OvernightDataRepository;
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

    // TODO Workflow for returning/pre-registered visitors

    @SpringBean
    private VisitorRepository visitorRepository;

    @SpringBean
    private VehicleRepository vehicleRepository;

    @SpringBean
    private OvernightDataRepository overnightDataRepository;

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

        final IModel<OvernightDataFormModel> overnightDataFormModel = Model.of(new OvernightDataFormModel());

        final OvernightDataForm overnightDataForm = new OvernightDataForm("overnightData", overnightDataFormModel);
        overnightDataForm.setOutputMarkupPlaceholderTag(true);
        overnightDataForm.setVisible(false);
        add(overnightDataForm);

        final OvernightDataConfirmationPanel overnightDataConfirmationPanel = new OvernightDataConfirmationPanel("overnightDataConfirmation", overnightDataFormModel);
        overnightDataConfirmationPanel.setOutputMarkupPlaceholderTag(true);
        overnightDataConfirmationPanel.setVisible(false);
        add(overnightDataConfirmationPanel);

        add(new BootstrapAjaxButton("visitorSubmit", Model.of("weiter"), visitorForm, Buttons.Type.Primary) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                VisitorFormModel modelObject = (VisitorFormModel) form.getModelObject();

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

                vehicleForm.setVisible(false);
                target.add(vehicleForm);

                vehicleConfirmation.setVisible(true);
                target.add(vehicleConfirmation);

                overnightDataForm.setVisible(true);
                target.add(overnightDataForm);
            }
        });

        add(new BootstrapAjaxButton("overnightDataSubmit", Model.of("weiter"), overnightDataForm, Buttons.Type.Primary) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                overnightDataForm.setVisible(false);
                target.add(overnightDataForm);

                overnightDataConfirmationPanel.setVisible(true);
                target.add(overnightDataConfirmationPanel);
            }
        });

        add(new BootstrapAjaxLink("finalConfirmation", Model.of("anmelden"), Buttons.Type.Primary) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // TODO Extract into service object (within a transaction)
                Visitor visitor = visitorRepository.save(createVisitor(visitorFormModel));
                Vehicle vehicle = vehicleRepository.save(createVehicle(vehicleFormModel));

                ConventionAttendance conventionAttendance = new ConventionAttendance();
                conventionAttendance.setConvention(BusfestSession.get().getActiveConvention().getObject());
                conventionAttendance.setVisitor(visitor);
                conventionAttendance.setVehicle(vehicle);

                // TODO Check for overnight stay
                OvernightData overnightData = overnightDataRepository.save(createOvernightData(overnightDataFormModel));
                conventionAttendance.setOvernightData(overnightData);

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

    private OvernightData createOvernightData(IModel<OvernightDataFormModel> overnightDataFormModel) {
        OvernightData overnightData = new OvernightData();
        OvernightDataFormModel object = overnightDataFormModel.getObject();
        overnightData.setFellowPassengers(object.getFellowPassengers());

        return overnightData;
    }

    private class VisitorForm extends BootstrapForm<VisitorFormModel> {
        private final IModel<VisitorFormModel> model;

        public VisitorForm(String componentId, IModel<VisitorFormModel> model) {
            super(componentId);
            this.model = model;
            setDefaultModel(new CompoundPropertyModel(model));
            add(new FormBehavior(FormType.Horizontal));
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
            setDefaultModel(new CompoundPropertyModel(model));
            add(new FormBehavior(FormType.Horizontal));
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            VehicleInputPanel vehicleInputPanel = new VehicleInputPanel("vehicleInput", model);
            add(vehicleInputPanel);
        }
    }

    private class OvernightDataForm extends BootstrapForm {
        private final IModel<OvernightDataFormModel> model;

        public OvernightDataForm(String componentId, IModel<OvernightDataFormModel> model) {
            super(componentId, model);
            this.model = model;
            setDefaultModel(new CompoundPropertyModel(model));
            add(new FormBehavior(FormType.Horizontal));
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            OvernightDataInputPanel overnightDataInputPanel = new OvernightDataInputPanel("overnightDataInput", model);
            add(overnightDataInputPanel);
        }
    }
}
