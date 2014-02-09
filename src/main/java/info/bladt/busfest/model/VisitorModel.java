package info.bladt.busfest.model;

import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VisitorModel extends LoadableDetachableModel<Visitor> {
    @SpringBean
    VisitorRepository visitorRepository;

    @Override
    protected Visitor load() {
        return new Visitor();
    }
}
