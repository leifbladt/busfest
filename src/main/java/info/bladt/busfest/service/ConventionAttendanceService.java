package info.bladt.busfest.service;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.OvernightDataFormModel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.OvernightData;
import info.bladt.busfest.persistence.Vehicle;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.persistence.repository.OvernightDataRepository;
import info.bladt.busfest.persistence.repository.VehicleRepository;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Component
public class ConventionAttendanceService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OvernightDataRepository overnightDataRepository;

    @Autowired
    private ConventionAttendanceRepository conventionAttendanceRepository;

    // TODO Use transaction
    public void createConventionAttendance(IModel<VisitorFormModel> visitorFormModel, IModel<VehicleFormModel> vehicleFormModel, IModel<OvernightDataFormModel> overnightDataFormModel) {
        Visitor visitor = visitorRepository.save(createVisitor(visitorFormModel));
        Vehicle vehicle = vehicleRepository.save(createVehicle(vehicleFormModel));

        ConventionAttendance conventionAttendance = new ConventionAttendance();
        conventionAttendance.setConvention(BusfestSession.get().getActiveConvention().getObject());
        conventionAttendance.setVisitor(visitor);
        conventionAttendance.setVehicle(vehicle);

        // TODO Check for overnight stay
        OvernightData overnightData = overnightDataRepository.save(createOvernightData(overnightDataFormModel));
        conventionAttendance.setOvernightData(overnightData);

        conventionAttendanceRepository.save(conventionAttendance);
    }

    private Visitor createVisitor(IModel<VisitorFormModel> visitorFormModel) {
        Visitor visitor = new Visitor();
        VisitorFormModel object = visitorFormModel.getObject();
        visitor.setFirstName(object.getFirstName());
        visitor.setLastName(object.getLastName());
        visitor.setStreet(object.getAddress());
        visitor.setZipCode(object.getZipCode());
        visitor.setCity(object.getCity());
        visitor.setCountry(object.getCountry());
        visitor.setDateOfBirth(object.getDateOfBirth());
        visitor.setTelephoneNumber(object.getTelephoneNumber());
        visitor.setEmailAddress(object.getEmailAddress());

        return visitor;
    }

    private Vehicle createVehicle(IModel<VehicleFormModel> vehicleFormModel) {
        Vehicle vehicle = new Vehicle();
        VehicleFormModel object = vehicleFormModel.getObject();
        vehicle.setType(object.getType());
        vehicle.setLicensePlateNumber(object.getLicensePlateNumber());

        return vehicle;
    }

    private OvernightData createOvernightData(IModel<OvernightDataFormModel> overnightDataFormModel) {
        OvernightData overnightData = new OvernightData();
        OvernightDataFormModel object = overnightDataFormModel.getObject();
        overnightData.setFellowPassengers(object.getFellowPassengers());

        return overnightData;
    }
}