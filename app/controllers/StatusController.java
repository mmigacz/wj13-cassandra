package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import controllers.dao.CassandraDAO;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.status;

public class StatusController extends Controller {

    public static Result displayStatus() {
        Metadata metadata = CassandraDAO.getClusterMetadata();
        return ok(status.render(metadata.getAllHosts()));
    }

}
