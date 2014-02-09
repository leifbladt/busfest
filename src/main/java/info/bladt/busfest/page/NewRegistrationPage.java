package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.VisitorModel;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration/new")
public class NewRegistrationPage extends BasePage {
    @SpringBean
    private VisitorRepository visitorRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final VisitorModel visitorModel = new VisitorModel();
        visitorModel.getObject().setFirstName("first name");

        VisitorForm visitorForm = new VisitorForm("visitor");

//        final BootstrapForm form = new BootstrapForm("visitor");
////        final Form<?> form = new Form("visitor");
//        final VisitorInputPanel visitorInputPanel = new VisitorInputPanel("visitorPanel", visitorModel);
//        form.add(visitorInputPanel);
//        form.add(new BootstrapAjaxButton("visitorNext", Buttons.Type.Primary) {
//            @Override
//            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
//                super.onSubmit(target, form);
//                visitorInputPanel.setVisible(false);
//                visitorModel.getObject().setFirstName("new first name");
//                target.add(form);
//            }
//        });
        add(visitorForm);
    }

    private class VisitorForm extends BootstrapForm {
        private String firstName;
        private String lastName;

        public VisitorForm(String componentId) {
            super(componentId);
            setDefaultModel(new CompoundPropertyModel(this));
            type(FormType.Horizontal);

            FormGroup nameGroup = new FormGroup("nameGroup", Model.of("Vorname / Nachname"));
            nameGroup.add(new TextField("firstName"));
            nameGroup.add(new TextField("lastName"));
            add(nameGroup);

            add(new AjaxSubmitLink("visitorSubmit") {
//            add(new BootstrapAjaxButton("visitorSubmit", Model.of("weiter"), Buttons.Type.Primary) {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);
                    System.out.println("onAjaxSubmit: " + firstName);
                }

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    super.onError(target, form);
                    System.out.println("onError!");
                }
            });
        }

        @Override
        protected void onSubmit() {
            super.onSubmit();

            System.out.println("onSubmit: " + firstName);
            Visitor visitor = new Visitor();
            visitor.setFirstName(firstName);
            visitor.setLastName(lastName);
            visitorRepository.save(visitor);
            System.out.println("count: " + visitorRepository.count());
        }
    }
}
