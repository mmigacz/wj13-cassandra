import controllers.dao.CassandraClient;
import play.GlobalSettings;
import play.Logger;



/**
 * Created by Maciej Migacz on 2014-05-05.
 * email: maciejmigacz@gmail.com
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(play.Application app) {
        super.onStart(app);
        CassandraClient.getInstance();
        Logger.info("Application has started");
    }

    @Override
    public void onStop(play.Application app) {
        super.onStop(app);
        Logger.info("Application shutdown...");
        CassandraClient.getInstance().close();
    }

}
