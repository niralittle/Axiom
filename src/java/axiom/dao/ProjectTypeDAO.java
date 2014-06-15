package axiom.dao;

import axiom.entity.ProjectType;
import java.util.List;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author user
 */
public interface ProjectTypeDAO extends GenericDAO<ProjectType>{

    public List<ProjectType> getAllProjectTypes()throws DBManagerException;

    public ProjectType getProjectTypeByDescription (String description)
            throws DBManagerException;
}