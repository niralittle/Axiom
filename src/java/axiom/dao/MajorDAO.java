package axiom.dao;

import axiom.entity.Major;
import java.util.List;
import axiom.dbmanager.DBManagerException;

/**
 *
 * @author Nira
 */
public interface MajorDAO extends GenericDAO<Major> {

    public Major getMajorByName(String name) throws DBManagerException;

    public List<Major> getAllMajors () throws DBManagerException;

    public List<Major> getMajorByFaculty(int facultyId) throws DBManagerException;

    void deleteMajor(Major major)throws DBManagerException;

    

}
