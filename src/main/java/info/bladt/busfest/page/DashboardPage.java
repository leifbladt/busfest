package info.bladt.busfest.page;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.repository.ConventionAttendanceProvisionRepository;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static info.bladt.busfest.persistence.specification.ConventionAttendanceSpecification.isConvention;
import static org.springframework.data.jpa.domain.Specifications.where;

public class DashboardPage extends AuthenticatedBasePage {
    // TODO Show current count of people (in a panel)
    // TODO Show fun facts (oldest car, ...)
    // TODO Show this year's prices (overnight, provisions)

    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @SpringBean
    private ConventionAttendanceProvisionRepository conventionAttendanceProvisionRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ConventionModel activeConvention = BusfestSession.get().getActiveConvention();
        Long busCount = conventionAttendanceRepository.count(where(isConvention(activeConvention.getObject())));
        Long tmpCount = conventionAttendanceProvisionRepository.count();

        // TODO Show correct label for 0/1/n count
        add(new Label("busCount", Model.of(busCount)));
        add(new Label("visitorCount", Model.of("487")));
        // TODO Show correct count of provisions for each day
        add(new Label("tmpCount", Model.of(tmpCount)));
    }
}
