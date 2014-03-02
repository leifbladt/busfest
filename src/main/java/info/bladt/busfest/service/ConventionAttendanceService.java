package info.bladt.busfest.service;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConfirmationFormModel;
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
    public void createConventionAttendance(
            IModel<VisitorFormModel> visitorFormModel,
            IModel<VehicleFormModel> vehicleFormModel,
            IModel<OvernightDataFormModel> overnightDataFormModel,
            IModel<ConfirmationFormModel> confirmationFormModel) {

        Visitor visitor = visitorRepository.save(createVisitor(visitorFormModel));
        Vehicle vehicle = vehicleRepository.save(createVehicle(vehicleFormModel));

        ConventionAttendance conventionAttendance = new ConventionAttendance();
        conventionAttendance.setConvention(BusfestSession.get().getActiveConvention().getObject());
        conventionAttendance.setVisitor(visitor);
        conventionAttendance.setVehicle(vehicle);
        conventionAttendance.setTotalCosts(confirmationFormModel.getObject().getTotalCosts());

        if (overnightDataFormModel.getObject().getOvernightVisitor()) {
            OvernightData overnightData = overnightDataRepository.save(createOvernightData(overnightDataFormModel));
            conventionAttendance.setOvernightData(overnightData);
        }

        conventionAttendanceRepository.save(conventionAttendance);
    }

    private Visitor createVisitor(IModel<VisitorFormModel> visitorFormModel) {
        VisitorFormModel object = visitorFormModel.getObject();
        Visitor visitor;
        if (object.getId() == null) {
            visitor = new Visitor();
        } else {
            visitor = visitorRepository.findOne(object.getId());
        }
        visitor.setId(object.getId());
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
        vehicle.setMonthOfManufacture(object.getMonthOfManufacture());
        vehicle.setYearOfManufacture(object.getYearOfManufacture());

        return vehicle;
    }

    private OvernightData createOvernightData(IModel<OvernightDataFormModel> overnightDataFormModel) {
        OvernightData overnightData = new OvernightData();
        OvernightDataFormModel object = overnightDataFormModel.getObject();
        overnightData.setOvernightCount(object.getOvernightCount());
        overnightData.setFellowPassengers(object.getFellowPassengers());
        overnightData.setCaravan(object.getCaravan());

        return overnightData;
    }
}
