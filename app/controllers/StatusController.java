package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import controllers.dao.CassandraClient;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.status;

public class StatusController extends Controller {

    public static Result displayStatus() {
        Cluster cluster = CassandraClient.getInstance().getCluster();
        Metadata metadata = cluster.getMetadata();
        return ok(status.render(metadata.getAllHosts()));
    }

}
