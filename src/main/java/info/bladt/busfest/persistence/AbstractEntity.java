package info.bladt.busfest.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:leif.bladt@1und1.de">Leif Bladt</a>
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }

    @PrePersist
    public void onPersist() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }
}
