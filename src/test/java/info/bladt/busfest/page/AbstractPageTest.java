package info.bladt.busfest.page;

import info.bladt.busfest.BusfestApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class AbstractPageTest {
    protected WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new BusfestApplication());
    }
}
