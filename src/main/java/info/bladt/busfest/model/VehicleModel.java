package info.bladt.busfest.model;

import info.bladt.busfest.persistence.Vehicle;
import info.bladt.busfest.persistence.repository.VehicleRepository;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class VehicleModel extends LoadableDetachableModel<Vehicle> {
    @SpringBean
    VehicleRepository vehicleRepository;

    private Long id;

    public VehicleModel() {
        Injector.get().inject(this);
    }

    public VehicleModel(Long id) {
        this();
        this.id = id;
    }

    @Override
    protected Vehicle load() {
        return vehicleRepository.findOne(id);
    }
}
