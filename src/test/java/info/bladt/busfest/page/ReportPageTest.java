package info.bladt.busfest.page;

import org.junit.Test;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ReportPageTest extends AbstractPageTest {
    @Test
    public void itRendersPageSuccessfully() {
        tester.startPage(ReportPage.class);
        tester.assertRenderedPage(ReportPage.class);
    }

    @Test
    public void mountPath() {
        tester.executeUrl("report");
        tester.assertRenderedPage(ReportPage.class);
    }
}
