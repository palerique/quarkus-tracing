package br.com.palerique;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@JBossLog
public class ProcessadorDeMensagens {

    @Incoming("piadas-in")
    @WithSession
    public Uni<Void> receberMensagem(String textoDaPiada) {
        log.infof("Recebendo piada: %s", textoDaPiada);
        final var piada = Piada.builder().texto(textoDaPiada).build();	
//    if (Math.random() > 0.5) {
//      log.error("Random exception occurred quando persistindo");
//      throw new RuntimeException("Random exception occurred");
//    }
        return Panache.withTransaction(piada::persist)
                .onItem()
                .ifNotNull()
                .transformToUni(piadaPersistida -> {
                    log.infof("Piada persistida: %s", piadaPersistida);
                    return Piada.count()
                            .onItem()
                            .ifNotNull()
                            .invoke(piadas -> log.infof("Quantidade de Piadas: %s", piadas))
                            .replaceWithVoid();
                });
    }

}
