package info.bladt.busfest.page;

import info.bladt.busfest.persistence.repository.ConventionAttendanceRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class DashboardPage extends AuthenticatedBasePage {
    // TODO Show current count of buses (in a panel)
    // TODO Show current count of people (in a panel)
    // TODO Show fun facts (oldest car, ...)
    // TODO Show this year's prices (overnight, provisioning)

    @SpringBean
    private ConventionAttendanceRepository conventionAttendanceRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // TODO Limit to current convention
        Long busCount = conventionAttendanceRepository.count();

        // TODO Show correct label for 0/1/n count
        add(new Label("busCount", Model.of(busCount)));
        add(new Label("visitorCount", Model.of("487")));
    }
}
