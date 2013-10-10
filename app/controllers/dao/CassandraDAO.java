package controllers.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;

public class CassandraDAO {
    public static Metadata getClusterMetadata() {
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Metadata metadata = cluster.getMetadata();
        return metadata;
    }
}
