package info.bladt.busfest.page;

import info.bladt.busfest.BusfestSession;
import info.bladt.busfest.model.ConventionModel;
import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static info.bladt.busfest.persistence.specification.ConventionAttendanceSpecification.isConvention;
import static org.springframework.data.jpa.domain.Specifications.where;

public class DashboardPage extends AuthenticatedBasePage {
    // TODO Show current count of people (in a panel)
    // TODO Show fun facts (oldest car, ...)
    // TODO Show this year's prices (overnight, provisioning)

    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ConventionModel activeConvention = BusfestSession.get().getActiveConvention();
        Long busCount = conventionAttendanceRepository.count(where(isConvention(activeConvention.getObject())));

        // TODO Show correct label for 0/1/n count
        add(new Label("busCount", Model.of(busCount)));
        add(new Label("visitorCount", Model.of("487")));
    }
}
