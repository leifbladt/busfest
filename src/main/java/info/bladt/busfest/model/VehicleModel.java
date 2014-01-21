package info.bladt.busfest.model;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VehicleModel extends LoadableDetachableModel<Vehicle> {
    @Override
    protected Vehicle load() {
        return new Vehicle();
    }
}
