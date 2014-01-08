package info.bladt;

import info.bladt.busfest.BusfestApplication;
import info.bladt.busfest.page.DashboardPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new BusfestApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(DashboardPage.class);

		//assert rendered page class
		tester.assertRenderedPage(DashboardPage.class);
	}
}
