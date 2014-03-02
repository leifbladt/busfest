package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.component.ConfirmationInputPanel;
import info.bladt.busfest.component.OvernightDataConfirmationPanel;
import info.bladt.busfest.component.OvernightDataInputPanel;
import info.bladt.busfest.component.SearchInputPanel;
import info.bladt.busfest.component.VehicleConfirmationPanel;
import info.bladt.busfest.component.VehicleInputPanel;
import info.bladt.busfest.component.VisitorConfirmationPanel;
import info.bladt.busfest.component.VisitorInputPanel;
import info.bladt.busfest.component.wizard.DefaultWizardStepListener;
import info.bladt.busfest.component.wizard.WizardModel;
import info.bladt.busfest.component.wizard.WizardStep;
import info.bladt.busfest.component.wizard.WizardStepListener;
import info.bladt.busfest.model.ConfirmationFormModel;
import info.bladt.busfest.model.OvernightDataFormModel;
import info.bladt.busfest.model.RegistrationSearchFormModel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.service.ConventionAttendanceService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration/new")
public class NewRegistrationPage extends AuthenticatedBasePage {

    // TODO Workflow for returning/pre-registered visitors
    // TODO Workflow for PDF output

    @SpringBean
    private ConventionAttendanceService conventionAttendanceService;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final IModel<RegistrationSearchFormModel> searchFormModel = Model.of(new RegistrationSearchFormModel());
        final IModel<VisitorFormModel> visitorFormModel = Model.of(new VisitorFormModel());
        final IModel<VehicleFormModel> vehicleFormModel = Model.of(new VehicleFormModel());
        final IModel<OvernightDataFormModel> overnightDataFormModel = Model.of(new OvernightDataFormModel());
        final IModel<ConfirmationFormModel> confirmationFormModel = Model.of(new ConfirmationFormModel(overnightDataFormModel));

        SearchForm searchForm = new SearchForm("search", searchFormModel);
        searchForm.setOutputMarkupPlaceholderTag(true);

        VisitorForm visitorForm = new VisitorForm("visitor", visitorFormModel);
        visitorForm.setOutputMarkupPlaceholderTag(true);
        visitorForm.setVisible(false);

        VisitorConfirmationPanel visitorConfirmation = new VisitorConfirmationPanel("visitorConfirmation", visitorFormModel);
        visitorConfirmation.setOutputMarkupPlaceholderTag(true);
        visitorConfirmation.setVisible(false);
        add(visitorConfirmation);

        VehicleForm vehicleForm = new VehicleForm("vehicle", vehicleFormModel);
        vehicleForm.setOutputMarkupPlaceholderTag(true);
        vehicleForm.setVisible(false);

        VehicleConfirmationPanel vehicleConfirmation = new VehicleConfirmationPanel("vehicleConfirmation", vehicleFormModel);
        vehicleConfirmation.setOutputMarkupPlaceholderTag(true);
        vehicleConfirmation.setVisible(false);
        add(vehicleConfirmation);

        OvernightDataForm overnightDataForm = new OvernightDataForm("overnightData", overnightDataFormModel);
        overnightDataForm.setOutputMarkupPlaceholderTag(true);
        overnightDataForm.setVisible(false);

        OvernightDataConfirmationPanel overnightDataConfirmationPanel = new OvernightDataConfirmationPanel("overnightDataConfirmation", overnightDataFormModel);
        overnightDataConfirmationPanel.setOutputMarkupPlaceholderTag(true);
        overnightDataConfirmationPanel.setVisible(false);
        add(overnightDataConfirmationPanel);

        ConfirmationForm confirmationForm = new ConfirmationForm("confirmation", confirmationFormModel);
        confirmationForm.setOutputMarkupPlaceholderTag(true);
        confirmationForm.setVisible(false);

        final WizardModel wizardModel = new WizardModel() {
            public void finish() {
                conventionAttendanceService.createConventionAttendance(visitorFormModel, vehicleFormModel, overnightDataFormModel, confirmationFormModel);
            }
        };
        WizardStep searchStep = new WizardStep(searchForm);
        wizardModel.add(searchStep);
        wizardModel.add(new WizardStep(visitorForm, visitorConfirmation));
        wizardModel.add(new WizardStep(vehicleForm, vehicleConfirmation));
        WizardStep overnightDataStep = new WizardStep(overnightDataForm, overnightDataConfirmationPanel);
        wizardModel.add(overnightDataStep);
        WizardStep confirmationStep = new WizardStep(confirmationForm);
        wizardModel.add(confirmationStep);

        // TODO Make initialization more comfortable
        wizardModel.setActiveStep(searchStep);

        final BootstrapForm form = new BootstrapForm("form") {
            @Override
            protected void onInitialize() {
                super.onInitialize();

                final WebMarkupContainer buttonBar = new WebMarkupContainer("buttonBar");
                buttonBar.setOutputMarkupId(true);

                buttonBar.add(new BootstrapAjaxButton("previous", Model.of("zurück"), Buttons.Type.Primary) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        target.add(buttonBar);

                        WizardStep activeStep = wizardModel.getActiveStep();
                        activeStep.getInputForm().setVisible(false);
                        target.add(activeStep.getInputForm());

                        wizardModel.previous();
                        WizardStep previousStep = wizardModel.getActiveStep();
                        previousStep.getInputForm().setVisible(true);
                        target.add(previousStep.getInputForm());

                        if (previousStep.getConfirmationPanel() != null) {
                            previousStep.getConfirmationPanel().setVisible(false);
                            target.add(previousStep.getConfirmationPanel());
                        }
                    }

                    @Override
                    public boolean isEnabled() {
                        return !wizardModel.isFirstStep();
                    }
                });
                buttonBar.add(new BootstrapAjaxButton("next", Model.of("weiter"), Buttons.Type.Primary) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        target.add(buttonBar);

                        WizardStep activeStep = wizardModel.getActiveStep();
                        activeStep.getInputForm().setVisible(false);
                        target.add(activeStep.getInputForm());

                        if (activeStep.getConfirmationPanel() != null) {
                            activeStep.getConfirmationPanel().setVisible(true);
                            target.add(activeStep.getConfirmationPanel());
                        }

                        wizardModel.next();
                        WizardStep nextStep = wizardModel.getActiveStep();
                        nextStep.getInputForm().setVisible(true);
                        target.add(nextStep.getInputForm());
                    }

                    @Override
                    public boolean isEnabled() {
                        return !wizardModel.isLastStep();
                    }
                });
                buttonBar.add(new BootstrapAjaxButton("finish", Model.of("fertigstellen"), Buttons.Type.Primary) {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        wizardModel.finish();
                        setResponsePage(RegistrationPage.class);
                    }

                    @Override
                    public boolean isEnabled() {
                        return wizardModel.isLastStep();
                    }
                });
                add(buttonBar);
            }
        };
        form.add(searchForm);
        form.add(visitorForm);
        form.add(vehicleForm);
        form.add(overnightDataForm);
        form.add(confirmationForm);
        add(form);

        searchStep.setStepListener(new DefaultWizardStepListener() {
            @Override
            public void onNext() {
                Visitor visitor = searchFormModel.getObject().getVisitor();
                // TODO Preselect vehicle
                // TODO Preselect provisions
                VisitorFormModel formModel = new VisitorFormModel();
                if (visitor != null) {
                    formModel.setId(visitor.getId());
                    formModel.setFirstName(visitor.getFirstName());
                    formModel.setLastName(visitor.getLastName());
                    formModel.setAddress(visitor.getStreet());
                    formModel.setZipCode(visitor.getZipCode());
                    formModel.setCity(visitor.getCity());
                    formModel.setCountry(visitor.getCountry());
                    formModel.setDateOfBirth(visitor.getDateOfBirth());
                    formModel.setEmailAddress(visitor.getEmailAddress());
                    formModel.setTelephoneNumber(visitor.getTelephoneNumber());
                }
                visitorFormModel.setObject(formModel);
            }
        });
    }

    private class SearchForm extends AbstractForm<RegistrationSearchFormModel> {

        private SearchForm(String componentId, IModel<RegistrationSearchFormModel> model) {
            super(componentId, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            SearchInputPanel searchInputPanel = new SearchInputPanel("searchInput", model);
            add(searchInputPanel);
        }
    }

    private class VisitorForm extends AbstractForm<VisitorFormModel> {

        public VisitorForm(String componentId, IModel<VisitorFormModel> model) {
            super(componentId, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            VisitorInputPanel visitorInputPanel = new VisitorInputPanel("visitorInput", model);
            add(visitorInputPanel);
        }
    }

    private class VehicleForm extends AbstractForm<VehicleFormModel> {

        public VehicleForm(String componentId, IModel<VehicleFormModel> model) {
            super(componentId, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            VehicleInputPanel vehicleInputPanel = new VehicleInputPanel("vehicleInput", model);
            add(vehicleInputPanel);
        }
    }

    private class OvernightDataForm extends AbstractForm<OvernightDataFormModel> {

        public OvernightDataForm(String componentId, IModel<OvernightDataFormModel> model) {
            super(componentId, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            OvernightDataInputPanel overnightDataInputPanel = new OvernightDataInputPanel("overnightDataInput", model);
            add(overnightDataInputPanel);
        }
    }

    private class ConfirmationForm extends AbstractForm<ConfirmationFormModel> {

        public ConfirmationForm(String componentId, IModel<ConfirmationFormModel> model) {
            super(componentId, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            ConfirmationInputPanel confirmationInputPanel = new ConfirmationInputPanel("confirmationInput", model);
            add(confirmationInputPanel);
        }
    }

    private abstract class AbstractForm<T> extends BootstrapForm {

        protected final IModel<T> model;

        protected AbstractForm(String componentId, IModel<T> model) {
            super(componentId, model);
            this.model = model;
            setDefaultModel(new CompoundPropertyModel(model));
            add(new FormBehavior(FormType.Horizontal));
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();
        }
    }
}
