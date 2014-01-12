package info.bladt.busfest;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import info.bladt.busfest.page.DashboardPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

public class BusfestApplication extends WebApplication
{
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
        new AnnotatedMountScanner().scanPackage("info.bladt").mount(this);

        Bootstrap.install(this, new BootstrapSettings());
//        BootstrapLess.install(this);
	}
}