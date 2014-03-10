package info.bladt.busfest.page;

import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.ConventionAttendanceProvision;
import info.bladt.busfest.persistence.Provision;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.provider.ConventionAttendanceProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static info.bladt.busfest.persistence.specification.ConventionAttendanceSpecification.isConvention;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ProvisionListPage extends AuthenticatedBasePage {

    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ConventionModel activeConvention = BusfestSession.get().getActiveConvention();
        conventionAttendanceRepository.findAll(where(isConvention(activeConvention.getObject())));

        Collection<Provision> provisionCollection = activeConvention.getObject().getProvisions();

        // TODO Filter to a single day
        // TODO Show only visitors with provisions
        // TODO Order by lastName, firstName
        // TODO Add total count column?

        List<IColumn> columns = new ArrayList<>();
        columns.add(new PropertyColumn(Model.of("Nummer"), "id"));
        columns.add(new PropertyColumn(Model.of("Nachname"), "visitor.lastName"));
        columns.add(new PropertyColumn(Model.of("Vorname"), "visitor.firstName"));
        for (Provision provision : provisionCollection) {
            columns.add(new ProvisionCountColumn(provision));
        }

        DefaultDataTable provisions = new DefaultDataTable("provisions", columns, new ConventionAttendanceProvider(), 250);
        provisions.add(new TableBehavior());
        add(provisions);
    }

    private class ProvisionCountColumn extends AbstractColumn<ConventionAttendance, String> {

        private Provision provision;

        public ProvisionCountColumn(Provision provision) {
            super(Model.of(provision.getDescription()));
            this.provision = provision;
        }

        @Override
        public void populateItem(Item<ICellPopulator<ConventionAttendance>> cellItem, String componentId, IModel<ConventionAttendance> rowModel) {
            int count = 0;
            Collection<ConventionAttendanceProvision> conventionAttendanceProvisions = rowModel.getObject().getConventionAttendanceProvisions();

            if (conventionAttendanceProvisions != null) {
                for (ConventionAttendanceProvision conventionAttendanceProvision : conventionAttendanceProvisions) {
                    if (conventionAttendanceProvision.getProvision().getId().equals(provision.getId())) {
                        count = conventionAttendanceProvision.getCount();
                    }
                }
            }

            if (count > 0) {
                cellItem.add(new Label(componentId, Model.of(count)));
            } else {
                cellItem.add(new Label(componentId, Model.of("--")));
            }
        }
    }
}
