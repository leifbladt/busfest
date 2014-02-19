package info.bladt.busfest.component;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import info.bladt.busfest.model.RegistrationSearchFormModel;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.ThrottlingSettings;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class SearchInputPanel extends Panel {

    @SpringBean
    public VisitorRepository visitorRepository;

    public SearchInputPanel(String id, final IModel<RegistrationSearchFormModel> model) {
        super(id, model);
        IModel<RegistrationSearchFormModel> compound = new CompoundPropertyModel<RegistrationSearchFormModel>(model);

        final BootstrapForm<RegistrationSearchFormModel> form = new BootstrapForm<RegistrationSearchFormModel>("search", compound);
        form.add(new FormBehavior(FormType.Horizontal));

        final List<Visitor> visitorChoices = new ArrayList();
        final ListChoice<Visitor> visitor = new ListChoice<Visitor>("visitor", visitorChoices) {
            @Override
            protected String getNullValidDisplayValue() {
                // TODO Use built-in behavior with translation key
                return "Neue Anmeldung";
            }
        };
        visitor.setChoiceRenderer(new IChoiceRenderer<Visitor>() {
            @Override
            public Object getDisplayValue(Visitor visitor) {
                // TODO Display more information
                String displayValue = String.format("%s %s", visitor.getFirstName(), visitor.getLastName());
                return displayValue;
            }

            @Override
            public String getIdValue(Visitor visitor, int index) {
                return visitor.getId().toString();
            }
        });
        visitor.setNullValid(true);
        visitor.setOutputMarkupId(true);
        form.add(visitor);

        // TODO Disable autocomplete on textfield
        TextField<String> query = new TextField<String>("query");
        query.add(new AjaxFormSubmitBehavior("onkeyup") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                visitorChoices.clear();

                String query1 = "%" + model.getObject().getQuery() + "%";
                List<Visitor> visitors = visitorRepository.findByFirstNameLikeOrLastNameLikeAllIgnoreCase(query1, query1);
                for (Visitor visitor : visitors) {
                    visitorChoices.add(visitor);
                }

                target.add(visitor);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.setThrottlingSettings(new ThrottlingSettings("id", Duration.ONE_SECOND, true));
            }
        });
        form.add(query);

        add(form);
    }
}
