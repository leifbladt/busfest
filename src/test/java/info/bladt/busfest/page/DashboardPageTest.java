package info.bladt.busfest.page;

import org.junit.Test;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class DashboardPageTest extends AbstractPageTest {
    @Test
    public void itRendersPageSuccessfully() {
        tester.startPage(DashboardPage.class);
        tester.assertRenderedPage(DashboardPage.class);
    }

    @Test
    public void mountPath() {
        tester.executeUrl("/");
        tester.assertRenderedPage(DashboardPage.class);
    }
}
