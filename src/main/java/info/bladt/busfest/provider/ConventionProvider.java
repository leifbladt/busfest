package info.bladt.busfest.provider;

import info.bladt.busfest.persistence.Convention;
import info.bladt.busfest.persistence.repository.ConventionRepository;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;

/**
 * Created by leif on 09.02.14.
 */
public class ConventionProvider implements ISortableDataProvider<Convention, String> {
    @SpringBean
    private ConventionRepository conventionRepository;

    public ConventionProvider() {
        Injector.get().inject(this);
    }

    @Override
    public Iterator<? extends Convention> iterator(long first, long count) {
        return conventionRepository.findAll().iterator();
    }

    @Override
    public long size() {
        return conventionRepository.count();
    }

    @Override
    public IModel<Convention> model(Convention convention) {
        return Model.of(convention);
    }

    @Override
    public void detach() {}

    @Override
    public ISortState<String> getSortState() {
        return null;
    }
}
