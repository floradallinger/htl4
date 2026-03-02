package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

// nur mit lombo dependency möglich
@Getter
@Setter
@Entity
public class Message extends PanacheEntity {
    // als instant für jba ein zeitstempel iso-8601
    private Instant timestamp;
    @Column(columnDefinition = "text")
    private String text;
    private String userId;
}