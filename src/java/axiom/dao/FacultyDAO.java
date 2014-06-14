package axiom.dao;

import java.util.List;
import axiom.entity.Faculty;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author Nira
 */

public interface FacultyDAO extends GenericDAO {

    public Faculty getFacultyByName(String name) throws DBManagerException;

    public List<Faculty> getAllFaculty() throws DBManagerException;

}
