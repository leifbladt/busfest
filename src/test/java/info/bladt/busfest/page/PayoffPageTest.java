package info.bladt.busfest.page;

import org.junit.Test;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class PayoffPageTest extends AbstractPageTest {
    @Test
    public void itRendersPageSuccessfully() {
        tester.startPage(PayoffPage.class);
        tester.assertRenderedPage(PayoffPage.class);
    }

    @Test
    public void mountPath() {
        tester.executeUrl("payoff");
        tester.assertRenderedPage(PayoffPage.class);
    }
}
