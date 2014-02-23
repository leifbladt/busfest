package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@Table(name = "provisions")
@Entity
public class Provision extends AbstractEntity {

    @Column(name = "description")
    private String description;

    // TODO Switch to joda-money
    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "convention_id")
    private Convention convention;
}
