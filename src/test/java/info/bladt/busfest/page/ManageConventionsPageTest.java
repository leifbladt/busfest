package info.bladt.busfest.page;

import org.junit.Test;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ManageConventionsPageTest extends AbstractPageTest {
    @Test
    public void itRendersPageSuccessfully() {
        tester.startPage(ManageConventionsPage.class);
        tester.assertRenderedPage(ManageConventionsPage.class);
    }

    @Test
    public void mountPath() {
        tester.executeUrl("conventions");
        tester.assertRenderedPage(ManageConventionsPage.class);
    }
}
