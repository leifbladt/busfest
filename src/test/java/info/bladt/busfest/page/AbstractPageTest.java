package info.bladt.busfest.page;

import info.bladt.busfest.BusfestApplication;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class AbstractPageTest {
    protected WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new BusfestApplication() {

            @Override
            public void init() {
                super.init();
                getSecuritySettings().setAuthorizationStrategy(IAuthorizationStrategy.ALLOW_ALL);
            }

            @Override
            public ServletContext getServletContext() {
                ServletContext servletContext = super.getServletContext();
                XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
                applicationContext.setConfigLocation("classpath:applicationContext.xml");
                applicationContext.setServletContext(servletContext);
                applicationContext.refresh();
                servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);

                return servletContext;
            }
        });
    }
}
