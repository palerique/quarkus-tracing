package br.com.palerique;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/iniciar")
@JBossLog
public class InicioResource {

  @Inject
  @Channel("piadas-out")
  MutinyEmitter<String> priceEmitter;

  @RestClient PiadaService piadaService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Counted
  @Timed
  public Uni<Response> iniciar() {
    log.info("Iniciando...");
    return piadaService
        .getPiadaRandomica()
        .onItem()
        .ifNotNull()
        .invoke(
            piada ->
                priceEmitter
                    .send(piada)
                    .subscribe()
                    .with(unused -> log.info("Enviada para o kafka: " + piada)))
        .onItem()
        .ifNotNull()
        .transform(entity -> Response.ok(entity).build())
        .onFailure()
        .recoverWithItem(
            (throwable) -> {
              log.error("Erro ao iniciar", throwable);
              return Response.serverError()
                  .entity(throwable.getMessage())
                  .status(Response.Status.BAD_REQUEST)
                  .build();
            });
  }
}
