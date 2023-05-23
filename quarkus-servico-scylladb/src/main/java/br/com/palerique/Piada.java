package br.com.palerique;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Piada extends PanacheEntityBase {
  @Id @GeneratedValue @UuidGenerator public UUID id;
  private String texto;
}
