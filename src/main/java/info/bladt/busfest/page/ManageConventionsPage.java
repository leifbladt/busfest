package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import info.bladt.busfest.persistence.repository.ConventionRepository;
import info.bladt.busfest.provider.ConventionProvider;
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
@MountPath("/conventions")
public class ManageConventionsPage extends AuthenticatedBasePage {
    @SpringBean
    ConventionRepository conventionRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<IColumn> columns = new ArrayList<IColumn>();
        columns.add(new PropertyColumn(Model.of("Treffen"), "convention"));
        columns.add(new PropertyColumn(Model.of("Ort"), "location"));
        columns.add(new PropertyColumn(Model.of("von"), "startsOn"));
        columns.add(new PropertyColumn(Model.of("bis"), "endsOn"));
        columns.add(new PropertyColumn(Model.of("Kosten Bus"), "overnightCostBus"));
        columns.add(new PropertyColumn(Model.of("Kosten Wohnwagen"), "overnightCostCaravan"));
        columns.add(new PropertyColumn(Model.of("Kosten Tagesgast"), "dayVisitorCost"));
        DefaultDataTable conventions = new DefaultDataTable("conventions", columns, new ConventionProvider(), 5);
        conventions.add(new TableBehavior());
        add(conventions);
    }
}
