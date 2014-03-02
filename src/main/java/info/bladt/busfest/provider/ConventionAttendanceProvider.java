package info.bladt.busfest.provider;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;

import static info.bladt.busfest.persistence.specification.ConventionAttendanceSpecification.isConvention;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by leif on 09.02.14.
 */
public class ConventionAttendanceProvider implements ISortableDataProvider<ConventionAttendance, String> {
    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    public ConventionAttendanceProvider() {
        Injector.get().inject(this);
    }

    @Override
    public Iterator<? extends ConventionAttendance> iterator(long first, long count) {
        ConventionModel activeConvention = BusfestSession.get().getActiveConvention();
        return conventionAttendanceRepository.findAll(where(isConvention(activeConvention.getObject()))).iterator();
    }

    @Override
    public long size() {
        ConventionModel activeConvention = BusfestSession.get().getActiveConvention();
        return conventionAttendanceRepository.count(where(isConvention(activeConvention.getObject())));
    }

    @Override
    public IModel<ConventionAttendance> model(ConventionAttendance conventionAttendance) {
        return Model.of(conventionAttendance);
    }

    @Override
    public void detach() {}

    @Override
    public ISortState<String> getSortState() {
        return null;
    }
}
