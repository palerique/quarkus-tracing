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
    public Uni<Void> processar(String mensagem) throws InterruptedException {
        log.infof("Processando mensagem: %s", mensagem);
        final var piada = Piada.builder().texto(mensagem).build();
        return Panache.withTransaction(piada::persist)
                .onItem()
                .ifNotNull()
                .transformToUni(
                        piadaPersistida -> {
                            log.infof("Piada persistida: %s", piadaPersistida);
                            return Piada.count()
                                    .onItem()
                                    .ifNotNull()
                                    .invoke(count -> log.infof("Total de piadas: %d", count))
                                    .replaceWithVoid();
                        });
    }
}
