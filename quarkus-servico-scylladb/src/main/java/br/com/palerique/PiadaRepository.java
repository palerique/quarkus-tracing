package br.com.palerique;

import com.datastax.driver.core.*;
import io.micrometer.core.annotation.Timed;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class PiadaRepository {

  private final Session session;
  private final PreparedStatement selectAll;
  private final PreparedStatement insert;
  private final PreparedStatement selectById;

  PiadaRepository(ScyllaClusterConfig scyllaClusterConfig) {
    Cluster cluster = scyllaClusterConfig.buildCluster();
    this.session = cluster.connect("piadas");
    selectAll = session.prepare("SELECT * FROM piadas");
    insert = session.prepare("INSERT INTO piadas (id, texto) VALUES (?, ?)");
    selectById = session.prepare("SELECT * FROM piadas WHERE id = ?");
  }

  @Timed
  @WithSpan
  Piada save(Piada piada) {
    final var id = UUID.randomUUID();
    BoundStatement bs = insert.bind(id, piada.getTexto());
    session.execute(bs);
    piada.setId(id);
    return piada;
  }

  @Timed
  @WithSpan
  Optional<Piada> findById(UUID id) {
    BoundStatement bs = selectById.bind(id);
    ResultSet rs = session.execute(bs);
    Row row = rs.one();
    if (row != null) {
      return Optional.of(
          Piada.builder().id(row.getUUID("id")).texto(row.getString("texto")).build());
    }
    return Optional.empty();
  }

  @Timed
  @WithSpan
  List<Piada> findAll() {
    return session.execute(selectAll.bind()).all().stream()
        .map(row -> Piada.builder().id(row.getUUID("id")).texto(row.getString("texto")).build())
        .toList();
  }
}
