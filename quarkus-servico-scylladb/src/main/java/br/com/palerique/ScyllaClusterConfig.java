package br.com.palerique;

import com.datastax.driver.core.Cluster;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ScyllaClusterConfig {

  @ConfigProperty(name = "scylla.db.contract-points")
  String contractPoints;

  @ConfigProperty(name = "scylla.db.cluster-name")
  String clusterName;

  public Cluster buildCluster() {
    return Cluster.builder()
        .withoutJMXReporting()
        .withClusterName(clusterName)
        .addContactPoint(contractPoints)
        .withPort(9042)
        .build();
  }
}
