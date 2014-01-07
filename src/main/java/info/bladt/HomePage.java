package info.bladt;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);


        AjaxLink link = new AjaxLink("registration") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(HomePage.class);
            }
        };
        add(link);
    }
}
