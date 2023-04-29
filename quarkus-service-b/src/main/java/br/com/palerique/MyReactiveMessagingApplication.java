package br.com.palerique;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.*;

@ApplicationScoped
@JBossLog
public class MyReactiveMessagingApplication {

  @Incoming("words-in")
  @NonBlocking
  @WithSession
  public Uni<Void> receiveMessage(String message) {
    log.infof("Received message: %s", message);
    final Piada piada = new Piada();
    piada.setTexto(message);
    return Panache.withTransaction(piada::persist)
        .onItem()
        .ifNotNull()
        .transform(
            piadaPersistida -> {
              log.infof("Persisted: %s", piadaPersistida);
              return piadaPersistida;
            })
        .replaceWithVoid();
  }
}
