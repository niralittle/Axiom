package axiom.dao.impl;

import axiom.dao.ProjectTypeDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.ProjectType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class ProjectTypeDAOImpl extends GenericDAOImpl<ProjectType>
        implements ProjectTypeDAO{

    public ProjectTypeDAOImpl(DBManager dbm) {
        super(dbm);
    }

    public List<ProjectType> getAllProjectTypes()throws DBManagerException{
        Statement statement = null;
    	List<ProjectType> sTypes;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM PROJECTTYPE " ;
        try {
            statement = dbManager.prepareStatement(query);
            ri = statement.executeQuery();
            sTypes = new ArrayList<ProjectType>();
            ProjectType type;
            while (ri.next()) {
                type = new ProjectType();
                type.setId(ri.getInt("ID"));
                type.setDescription(ri.getString("DESCRIPTION"));
                sTypes.add(type);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return sTypes;

    }

    public ProjectType getProjectTypeByDescription (String description)
            throws DBManagerException
    {


        Statement statement = null;
    	ProjectType p = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM PROJECTTYPE " +
                        "WHERE DESCRIPTION = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, description);
            ri = statement.executeQuery();
            p.setId(ri.getInt("id"));
            p.setDescription(description);

        } catch (DBManagerException exc) {
                throw new DBManagerException ("An error occured, " +
                                "contact the administrator");
        } finally {
                statement.close();
        }
        return p;
    }

 
}
