package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Message extends PanacheEntity {

    private Instant timestamp;

    @Column(columnDefinition = "text")
    private String text;

    private String userId;
}
