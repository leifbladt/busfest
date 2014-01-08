package info.bladt;

import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class BasePage extends WebPage {
    public BasePage() {}

    public BasePage(IModel<?> model) {
        super(model);
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        HtmlTag html = new HtmlTag("html");
        add(html);

        Navbar navbar = new Navbar("navbar");
        navbar.brandName(Model.of("busfest"));
        navbar.addComponents(new ImmutableNavbarComponent(new NavbarButton<HomePage>(HomePage.class, Model.of("Home"))));

        add(navbar);
    }
}
