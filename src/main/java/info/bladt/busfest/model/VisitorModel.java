package info.bladt.busfest.model;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VisitorModel extends LoadableDetachableModel<Visitor> {
    @Override
    protected Visitor load() {
        return new Visitor();
    }
}
