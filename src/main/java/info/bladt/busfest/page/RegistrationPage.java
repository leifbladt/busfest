package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.provider.ConventionAttendanceProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends AuthenticatedBasePage {
    @SpringBean
    ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        BootstrapBookmarkablePageLink<NewRegistrationPage> newRegistration = new BootstrapBookmarkablePageLink<NewRegistrationPage>("newRegistration", NewRegistrationPage.class, Buttons.Type.Primary);
        newRegistration.setLabel(Model.of("Neue Anmeldung"));
        add(newRegistration);

        IModel pdfModel = new LoadableDetachableModel<File>() {
            @Override
            protected File load() {
                try {
                    return createPdfFile();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        };

        DownloadLink downloadPdf = new DownloadLink("downloadPdf", pdfModel, "anmeldung.pdf");
        downloadPdf.setDeleteAfterDownload(true);
        add(downloadPdf);

        List<IColumn> columns = new ArrayList<IColumn>();
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

    private File createPdfFile() throws Exception {
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.moveTextPositionByAmount( 100, 700 );
        contentStream.drawString( "Hello World" );
        contentStream.endText();

        // Make sure that the content stream is closed:
        contentStream.close();

        // Save the results and ensure that the document is properly closed:
        document.save("anmeldung.pdf");
        document.close();

        return new File("anmeldung.pdf");
    }
}
