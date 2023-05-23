package br.com.palerique;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.*;

@ApplicationScoped
@JBossLog
@RequiredArgsConstructor
public class MyReactiveMessagingApplication {

  private final PiadaRepository repository;

  @Incoming("piadas-in-scylladb")
  public Uni<Void> receiveMessage(String message) {
    log.infof("Received message (ScyllaDB): %s", message);
    //    TODO: Uncomment this to see the exception observability
    //    if (Math.random() > 0.5) {
    //      log.error("Random exception occurred quando persistindo");
    //      throw new RuntimeException("Random exception occurred");
    //    }
    final var saved = repository.save(Piada.builder().texto(message).build());
    log.info("piada salva no ScyllaDB: %s".formatted(saved));
    log.info("listando piadas salvas no ScyllaDB");
    log.info("piadas salvas no ScyllaDB: %d".formatted(repository.findAll().size()));
    return Uni.createFrom().voidItem();
  }
}
