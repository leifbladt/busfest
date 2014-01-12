package info.bladt.busfest.page;

import org.junit.Test;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class RegistrationPageTest extends AbstractPageTest {
    @Test
    public void itRendersPageSuccessfully() {
        tester.startPage(RegistrationPage.class);
        tester.assertRenderedPage(RegistrationPage.class);
    }

    @Test
    public void mountPath() {
        tester.executeUrl("registration");
        tester.assertRenderedPage(RegistrationPage.class);
    }
}
