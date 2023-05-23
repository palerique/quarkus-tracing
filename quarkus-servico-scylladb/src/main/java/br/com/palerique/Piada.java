package br.com.palerique;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Piada {
  private UUID id;
  private String texto;
}
