package info.bladt.busfest;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.less.BootstrapLess;
import info.bladt.busfest.page.DashboardPage;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

public class BusfestApplication extends AuthenticatedWebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return DashboardPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

        // Scan for @MountPath annotation and mounts them
        new AnnotatedMountScanner().scanPackage("info.bladt.busfest").mount(this);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

//        // JPA
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext();
//        VisitorRepository repository = context.getBean(VisitorRepository.class);


        // Bootstrap
        Bootstrap.install(this, new BootstrapSettings());
        BootstrapLess.install(this);
	}

    @Override
    public Session newSession(Request request, Response response) {
        return new BusfestSession(request);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return BusfestSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }
}
