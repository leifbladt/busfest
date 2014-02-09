package info.bladt.busfest.model;

import info.bladt.busfest.persistence.Convention;
import info.bladt.busfest.persistence.repository.ConventionRepository;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by leif on 09.02.14.
 */
public class ConventionModel extends LoadableDetachableModel<Convention> {
    @SpringBean
    private ConventionRepository conventionRepository;

    private Long id;

    public ConventionModel() {
        Injector.get().inject(this);
    }

    public ConventionModel(Long id) {
        this();
        this.id = id;
    }

    @Override
    protected Convention load() {
        return conventionRepository.findOne(id);
    }
}
