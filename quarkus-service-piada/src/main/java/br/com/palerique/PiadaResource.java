package br.com.palerique;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Locale;
import lombok.extern.jbosslog.JBossLog;
import net.datafaker.Faker;

@Path("/piadas")
@JBossLog
public class PiadaResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<String> obterPiadaAleatoria() {
    log.info("Obtendo piada aleatória...");
    //    if (Math.random() > 0.5) {
    //      log.error("Excecaoo aleatoria ocorreu em PiadaResource");
    //      throw new RuntimeException("Excecaoo aleatoria ocorreu em PiadaResource");
    //    }
    return Uni.createFrom().item(new Faker(new Locale("pt-BR")).chuckNorris().fact());
  }
}
