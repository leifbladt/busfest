package info.bladt.busfest.page;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("report")
public class ReportPage extends AuthenticatedBasePage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BookmarkablePageLink<ProvisionListPage>("provisionList", ProvisionListPage.class));
    }
}
