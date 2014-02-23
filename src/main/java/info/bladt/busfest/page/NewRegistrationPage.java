package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.component.OvernightDataConfirmationPanel;
import info.bladt.busfest.component.OvernightDataInputPanel;
import info.bladt.busfest.component.SearchInputPanel;
import info.bladt.busfest.component.VehicleConfirmationPanel;
import info.bladt.busfest.component.VehicleInputPanel;
import info.bladt.busfest.component.VisitorConfirmationPanel;
import info.bladt.busfest.component.VisitorInputPanel;
import info.bladt.busfest.model.OvernightDataFormModel;
import info.bladt.busfest.model.RegistrationSearchFormModel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.service.ConventionAttendanceService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;

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

        final SearchForm searchForm = new SearchForm("search", searchFormModel);
        searchForm.setOutputMarkupPlaceholderTag(true);
        add(searchForm);

        final IModel<VisitorFormModel> visitorFormModel = Model.of(new VisitorFormModel());

        final VisitorForm visitorForm = new VisitorForm("visitor", visitorFormModel);
        visitorForm.setOutputMarkupPlaceholderTag(true);
        visitorForm.setVisible(false);
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

        final Label totalCosts = new Label("totalCosts", new PropertyModel(overnightDataFormModel, "totalCosts"));
        totalCosts.setOutputMarkupPlaceholderTag(true);
        totalCosts.setVisible(false);
        add(totalCosts);
        final BootstrapAjaxLink confirmationLink = new BootstrapAjaxLink("finalConfirmation", Model.of("anmelden"), Buttons.Type.Primary) {
            @Override
            public void onClick(AjaxRequestTarget target) {
                conventionAttendanceService.createConventionAttendance(visitorFormModel, vehicleFormModel, overnightDataFormModel);
                setResponsePage(RegistrationPage.class);
            }
        };
        confirmationLink.setOutputMarkupPlaceholderTag(true);
        confirmationLink.setVisible(false);
        add(confirmationLink);

        searchForm.setStepListener(new WizardStepListener() {
            @Override
            public void onNext(AjaxRequestTarget target) {
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

                searchForm.setVisible(false);
                target.add(searchForm);

                visitorForm.setVisible(true);
                target.add(visitorForm);
            }

            @Override
            public void onPrevious(AjaxRequestTarget target) {}
        });

        visitorForm.setStepListener(new WizardStepListener() {
            @Override
            public void onNext(AjaxRequestTarget target) {
                visitorForm.setVisible(false);
                target.add(visitorForm);

                visitorConfirmation.setVisible(true);
                target.add(visitorConfirmation);

                vehicleForm.setVisible(true);
                target.add(vehicleForm);
            }

            @Override
            public void onPrevious(AjaxRequestTarget target) {
                searchForm.setVisible(true);
                target.add(searchForm);

                visitorForm.setVisible(false);
                target.add(visitorForm);
            }
        });

        vehicleForm.setStepListener(new WizardStepListener() {
            @Override
            public void onNext(AjaxRequestTarget target) {
                vehicleForm.setVisible(false);
                target.add(vehicleForm);

                vehicleConfirmation.setVisible(true);
                target.add(vehicleConfirmation);

                overnightDataForm.setVisible(true);
                target.add(overnightDataForm);
            }

            @Override
            public void onPrevious(AjaxRequestTarget target) {
                visitorForm.setVisible(true);
                target.add(visitorForm);

                visitorConfirmation.setVisible(false);
                target.add(visitorConfirmation);

                vehicleForm.setVisible(false);
                target.add(vehicleForm);
            }
        });

        overnightDataForm.setStepListener(new WizardStepListener() {
            @Override
            public void onNext(AjaxRequestTarget target) {
                overnightDataForm.setVisible(false);
                target.add(overnightDataForm);

                overnightDataConfirmationPanel.setVisible(true);
                target.add(overnightDataConfirmationPanel);

                confirmationLink.setVisible(true);
                target.add(confirmationLink);
                totalCosts.setVisible(true);
                target.add(totalCosts);
            }

            @Override
            public void onPrevious(AjaxRequestTarget target) {
                vehicleForm.setVisible(true);
                target.add(vehicleForm);

                vehicleConfirmation.setVisible(false);
                target.add(vehicleConfirmation);

                overnightDataForm.setVisible(false);
                target.add(overnightDataForm);
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

        @Override
        protected String nextButtonId() {
            return "searchSubmit";
        }

        @Override
        protected String previousButtonId() {
            return "searchPrevious";
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

        @Override
        protected String nextButtonId() {
            return "visitorSubmit";
        }

        @Override
        protected String previousButtonId() {
            return "visitorPrevious";
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

        @Override
        protected String nextButtonId() {
            return "vehicleSubmit";
        }

        @Override
        protected String previousButtonId() {
            return "vehiclePrevious";
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

        @Override
        protected String nextButtonId() {
            return "overnightDataSubmit";
        }

        @Override
        protected String previousButtonId() {
            return "overnightDataPrevious";
        }
    }

    private abstract class AbstractForm<T> extends BootstrapForm {

        protected WizardStepListener stepListener;

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

            add(new BootstrapAjaxButton(this.nextButtonId(), Model.of("weiter"), Buttons.Type.Primary) {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);
                    if (stepListener != null) {
                        stepListener.onNext(target);
                    }
                }
            });

            add(new BootstrapAjaxButton(this.previousButtonId(), Model.of("zurück"), Buttons.Type.Primary) {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);
                    if (stepListener != null) {
                        stepListener.onPrevious(target);
                    }
                }
            });
        }

        protected abstract String nextButtonId();

        protected abstract String previousButtonId();

        // TODO Encapsulate components the right (aka wicket) way
        public void setStepListener(WizardStepListener stepListener) {
            this.stepListener = stepListener;
        }
    }

    private interface WizardStepListener extends Serializable {
        public void onPrevious(AjaxRequestTarget target);

        public void onNext(AjaxRequestTarget target);
    }
}
