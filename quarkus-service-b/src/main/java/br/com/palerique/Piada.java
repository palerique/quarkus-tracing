package br.com.palerique;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@ToString(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Piada extends PanacheEntity {
    private String texto;
}
