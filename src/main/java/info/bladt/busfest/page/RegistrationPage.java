package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.provider.ConventionAttendanceProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MountPath("/registration")
public class RegistrationPage extends BasePage {
    @SpringBean
    ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        BootstrapBookmarkablePageLink<NewRegistrationPage> newRegistration = new BootstrapBookmarkablePageLink<NewRegistrationPage>("newRegistration", NewRegistrationPage.class, Buttons.Type.Primary);
        newRegistration.setLabel(Model.of("Neue Anmeldung"));
        add(newRegistration);

        List<IColumn> columns = new ArrayList<IColumn>();
        columns.add(new PropertyColumn(Model.of("Nachname"), "visitor.lastName"));
        columns.add(new PropertyColumn(Model.of("Vorname"), "visitor.firstName"));
        columns.add(new PropertyColumn(Model.of("Fahrzeugtyp"), "vehicle.type"));
        columns.add(new PropertyColumn(Model.of("Kennzeichen"), "vehicle.licensePlateNumber"));
        DefaultDataTable latestAttendances = new DefaultDataTable("latestAttendances", columns, new ConventionAttendanceProvider(), 5);
        latestAttendances.add(new TableBehavior());
        add(latestAttendances);
    }
}
