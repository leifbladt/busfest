package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.Convention;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public abstract class BasePage extends WebPage {
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

        NavbarDropDownButton settings = new NavbarDropDownButton(Model.of("Einstellungen")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                ArrayList<AbstractLink> links = new ArrayList<AbstractLink>();
                links.add(new MenuBookmarkablePageLink<PayoffPage>(PayoffPage.class, Model.of("Treffen verwalten")));
                links.add(new MenuDivider());
                return links;
            }
        };

        Convention activeConvention = BusfestSession.get().getActiveConvention();
        Navbar navbar = new Navbar("navbar");
        navbar.setPosition(Navbar.Position.STATIC_TOP);
        navbar.brandName(Model.of(activeConvention.getDisplayName()));
        navbar.addComponents(
                new ImmutableNavbarComponent(new NavbarButton<BasePage>(DashboardPage.class, Model.of("Dashboard"))),
                new ImmutableNavbarComponent(new NavbarButton<DashboardPage>(RegistrationPage.class, Model.of("Anmeldung"))),
                new ImmutableNavbarComponent(new NavbarButton<DashboardPage>(ReportPage.class, Model.of("Auswertung"))),
                new ImmutableNavbarComponent(new NavbarButton<DashboardPage>(PayoffPage.class, Model.of("Abrechnung"))),
                new ImmutableNavbarComponent(settings, Navbar.ComponentPosition.RIGHT)
        );

        add(navbar);
    }
}
