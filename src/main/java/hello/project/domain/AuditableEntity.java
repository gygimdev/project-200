package hello.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
public class AuditableEntity {

    @Column(updatable = false)
    protected LocalDateTime created_at;
    protected LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.created_at = now;
        this.updated_at = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now(ZoneOffset.UTC);
    }
}
