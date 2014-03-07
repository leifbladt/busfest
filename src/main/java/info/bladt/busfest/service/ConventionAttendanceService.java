package info.bladt.busfest.service;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConfirmationFormModel;
import info.bladt.busfest.model.OvernightDataFormModel;
import info.bladt.busfest.model.ProvisionFormModel;
import info.bladt.busfest.model.VehicleFormModel;
import info.bladt.busfest.model.VisitorFormModel;
import info.bladt.busfest.persistence.ConventionAttendance;
import info.bladt.busfest.persistence.ConventionAttendanceProvision;
import info.bladt.busfest.persistence.OvernightData;
import info.bladt.busfest.persistence.Provision;
import info.bladt.busfest.persistence.Vehicle;
import info.bladt.busfest.persistence.Visitor;
import info.bladt.busfest.persistence.repository.ConventionAttendanceProvisionRepository;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import info.bladt.busfest.persistence.repository.OvernightDataRepository;
import info.bladt.busfest.persistence.repository.ProvisionRepository;
import info.bladt.busfest.persistence.repository.VehicleRepository;
import info.bladt.busfest.persistence.repository.VisitorRepository;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static info.bladt.busfest.persistence.specification.ConventionAttendanceSpecification.isLikeName;
import static org.springframework.data.jpa.domain.Specifications.where;

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
    private ProvisionRepository provisionRepository;

    @Autowired
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @Autowired
    private ConventionAttendanceProvisionRepository conventionAttendanceProvisionRepository;

    // TODO Use transaction
    public Long createConventionAttendance(
            IModel<VisitorFormModel> visitorFormModel,
            IModel<VehicleFormModel> vehicleFormModel,
            IModel<OvernightDataFormModel> overnightDataFormModel,
            IModel<ProvisionFormModel> provisionFormModelIModel,
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

        conventionAttendance = conventionAttendanceRepository.save(conventionAttendance);

        if (overnightDataFormModel.getObject().getOvernightVisitor()) {
            // TODO Set correct date
            // TODO Only create when count>0
            ProvisionFormModel provisionFormModel = provisionFormModelIModel.getObject();
            createConventionAttendanceProvision(conventionAttendance, 1L, new Date(), provisionFormModel.getP1CountSat());
            createConventionAttendanceProvision(conventionAttendance, 2L, new Date(), provisionFormModel.getP2CountSat());
            createConventionAttendanceProvision(conventionAttendance, 3L, new Date(), provisionFormModel.getP3CountSat());
            createConventionAttendanceProvision(conventionAttendance, 4L, new Date(), provisionFormModel.getP1CountSun());
            createConventionAttendanceProvision(conventionAttendance, 5L, new Date(), provisionFormModel.getP2CountSun());
            createConventionAttendanceProvision(conventionAttendance, 6L, new Date(), provisionFormModel.getP3CountSun());
        }

        return conventionAttendance.getId();
    }

    private ConventionAttendanceProvision createConventionAttendanceProvision(ConventionAttendance conventionAttendance, Long provisionId, Date deliveredOn, int count) {
        Provision provision = provisionRepository.findOne(provisionId);
        ConventionAttendanceProvision conventionAttendanceProvision = new ConventionAttendanceProvision();
        conventionAttendanceProvision.setConventionAttendance(conventionAttendance);
        conventionAttendanceProvision.setProvision(provision);
        conventionAttendanceProvision.setDeliveredOn(deliveredOn);
        conventionAttendanceProvision.setCount(count);
        return conventionAttendanceProvisionRepository.save(conventionAttendanceProvision);
    }

    public List<ConventionAttendance> findReturningVisitors(String query) {
        // TODO Return visitor instead
        // TODO License plate

        if (query == null) {
            return new ArrayList<>();
        } else {
            return conventionAttendanceRepository.findAll(where(isLikeName(query)));
        }
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
