package axiom.controllers;

/**
 *
 * @author user
 */
import axiom.dao.StartupDAO;
import axiom.dao.impl.StartupDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.entity.Startup;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NewStartup extends Controller{

    public int createNewStartup(String name, String description, int ownerId,
                        int projectType, int startupStateID){
        StartupDAO startupDAO = null;
        int startupId = 0;
        try{
            if (isInternal) {
                dbManager = new DBManager();
            }
            startupDAO = new StartupDAOImpl(dbManager);
            Startup start = new Startup();
            start.setName(name);
            start.setDescription(description);
            start.setOwnerId(ownerId);
            start.setProjectType(projectType);
            start.setStartupStateID(startupStateID);

            startupId = (Integer) startupDAO.createNewStartup(start);

            if (isInternal) {
                dbManager.commit();
            }

        }catch(DBManagerException ex){
            if (isInternal)
                Logger.getLogger(NewStartup.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (isInternal)
                dbManager.close();
        }
        return startupId;
    }

}
