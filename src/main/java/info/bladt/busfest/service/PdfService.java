package info.bladt.busfest.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import info.bladt.busfest.persistence.ConventionAttendance;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Component
public class PdfService {

    public static final float[][] COLUMNS = {
            { 36, 36, 296, 806 } , { 299, 36, 559, 806 }
    };

    public File createRegistrationPdf(ConventionAttendance conventionAttendance) throws Exception {
        // TODO Create in memory

        Font smallFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
        Font headingFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        document.setPageSize(PageSize.A4);
        document.setMargins(60, 60, 60, 108);

        document.open();

        document.add(new Paragraph("Teilnehmerdaten", headingFont));
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Organisatorisches"));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Kostenübersicht"));
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        document.add(table);

        document.add(new Paragraph(
                "Haftungsausschluss - rechtlicher Hinweis:\n" +
                "Im Rahmen des Haftungsausschlusses nehmen die Teilnehmer auf eigene Gefahr an der Veranstaltung teil. Sie tragen die alleinige zivil- und strafrechtliche Verantwortung für alle von ihnen oder dem von ihnen benutzten Fahrzeug verursachten Schäden.\n" +
                        "Der Haftungsausschluss wird mit Abgabe der verbindlichen Anmeldung allen Beteiligten gegenüber wirksam.\n" +
                        "Die Veranstaltung ist nach den Bestimmungen der Straßenverkehrsordnung (StVO), der Straßenverkehrszulassungsordnung (StVZO) und den Auflagen der zuständigen Erlaubnisbehörde ausgerichtet, die die Teilnehmer mit Abgabe der verbindlichen Anmeldung anerkennen."
        , smallFont));

        document.add(new Paragraph(
                "Während der Veranstaltung werden Fotos und Filme gefertigt, die unter Umständen im Internet veröffentlicht werden. Mit Abgabe der Anmeldung erkennt dies jeder Teilnehmer an."
        , smallFont));

        document.setPageSize(PageSize.A6.rotate());
        document.newPage();

        document.add(new Paragraph("Test"));
        document.close();

        return new File("test.pdf");
    }
}
