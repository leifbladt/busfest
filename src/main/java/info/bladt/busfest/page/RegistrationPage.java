package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import info.bladt.busfest.provider.ConventionAttendanceProvider;
import info.bladt.busfest.service.PdfService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends AuthenticatedBasePage {

    @SpringBean
    PdfService pdfService;

    @SpringBean
    VisitorRepository visitorRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        BootstrapBookmarkablePageLink<NewRegistrationPage> newRegistration = new BootstrapBookmarkablePageLink<>("newRegistration", NewRegistrationPage.class, Buttons.Type.Primary);
        newRegistration.setLabel(Model.of("Neue Anmeldung"));
        add(newRegistration);

        // TODO Customize filename with id and name
        DownloadLink downloadPdf = new DownloadLink("downloadPdf", new PdfFileModel(), "anmeldung.pdf");
        downloadPdf.setDeleteAfterDownload(true);
        add(downloadPdf);

        List<IColumn> columns = new ArrayList<>();
        columns.add(new PropertyColumn(Model.of("Nummer"), "id"));
        columns.add(new PropertyColumn(Model.of("Nachname"), "visitor.lastName"));
        columns.add(new PropertyColumn(Model.of("Vorname"), "visitor.firstName"));
        columns.add(new PropertyColumn(Model.of("Fahrzeugtyp"), "vehicle.type"));
        columns.add(new PropertyColumn(Model.of("Kennzeichen"), "vehicle.licensePlateNumber"));
        columns.add(new PropertyColumn(Model.of("Übernachtungen"), "overnightData.overnightCount"));
        columns.add(new PropertyColumn(Model.of("Mitreisende"), "overnightData.fellowPassengers"));
        DefaultDataTable latestAttendances = new DefaultDataTable("latestAttendances", columns, new ConventionAttendanceProvider(), 5);
        latestAttendances.add(new TableBehavior());
        add(latestAttendances);
    }

    private class PdfFileModel extends LoadableDetachableModel<File> {

        @Override
        protected File load() {
            try {
                ConventionAttendance conventionAttendance = new ConventionAttendance();

                return pdfService.createRegistrationPdf(conventionAttendance);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
