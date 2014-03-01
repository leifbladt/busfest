package info.bladt.busfest.model;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.persistence.Convention;
import org.apache.wicket.model.IModel;
import org.joda.money.Money;

import java.io.Serializable;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
public class ConfirmationFormModel implements Serializable {

    private IModel<OvernightDataFormModel> overnightDataFormModel;

    public ConfirmationFormModel(IModel<OvernightDataFormModel> overnightDataFormModel) {
        this.overnightDataFormModel = overnightDataFormModel;
    }

    public Money getTotalCosts() {
        Convention activeConvention = BusfestSession.get().getActiveConvention().getObject();
        OvernightDataFormModel overnightData = overnightDataFormModel.getObject();

        if (overnightData.getOvernightVisitor() != null && overnightData.getOvernightVisitor()) {
            Money cost = activeConvention.getOvernightCostBus().multipliedBy(overnightData.getOvernightCount());

            if (overnightData.getCaravan() != null && overnightData.getCaravan()) {
                cost = cost.plus(activeConvention.getOvernightCostCaravan().multipliedBy(overnightData.getOvernightCount()));
            }

            return cost;
        } else {
            return activeConvention.getDayVisitorCost();
        }
    }

    public IModel<OvernightDataFormModel> getOvernightDataFormModel() {
        return overnightDataFormModel;
    }

    public void setOvernightDataFormModel(IModel<OvernightDataFormModel> overnightDataFormModel) {
        this.overnightDataFormModel = overnightDataFormModel;
    }
}
