 package axiom.dao.impl;

import axiom.dao.VacancyDAO;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.dbmanager.ResultIterator;
import axiom.dbmanager.Statement;
import axiom.entity.Vacancy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nira
 */
public class VacancyDAOImpl extends GenericDAOImpl<Vacancy> implements VacancyDAO {

    public VacancyDAOImpl(DBManager dbm) {
        super(dbm);
    }
    public List<Vacancy> getVacanciesBySkill(int skillID,
            int offset, int numberOfRecords) throws DBManagerException {
        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}

    	Statement statement = null;
    	List<Vacancy> li = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( " +
                        "SELECT v.* " +
                        "FROM VACANCY v, (SELECT * FROM VACANCYSKILL " +
                        "                 WHERE SKILLID = ?) vs " +
                        "WHERE v.ID = vs.VACANCYID " +
                        "ORDER BY v.CREATEDON " +
                        " ) a where ROWNUM <= ? ) " +
                        "WHERE rnum  > ? ";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, skillID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);

            ri = statement.executeQuery();
            li = new ArrayList<Vacancy>();
            Vacancy u;
            while (ri.next()) {
                u = new Vacancy();
                u.setID(ri.getInt("id"));
                u.setName(ri.getString("NAME"));
                u.setDescription(ri.getString("DESCRIPTION"));
                u.setDate(ri.getDate("CREATEDON"));
                u.setStartupID(ri.getInt("STARTUPID"));
                li.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return li;
    }

    public List<Vacancy> getVacancyByStartup(int startupID,
            int offset, int numberOfRecords) throws DBManagerException {

        if (numberOfRecords < 1 || offset < 0) {
            throw new DBManagerException("Illegal argument in paging - less than 1. "
                            + " Can't proccess the request!");
    	}
    	Statement statement = null;
    	List<Vacancy> li = null;
    	ResultIterator ri  = null;
    	String query  = "SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( " +
                        "SELECT v.* " +
                        "FROM VACANCY v " +
                        "WHERE v.STARTUPID = ? " +
                        "ORDER BY v.CREATEDON " +
                        " ) a where ROWNUM <= ? )" +
                        "WHERE rnum  > ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setInt(1, startupID);
            statement.setInt(2, offset + numberOfRecords);
            statement.setInt(3, offset);

            ri = statement.executeQuery();
            li = new ArrayList<Vacancy>();
            Vacancy u;
            while (ri.next()) {
                u = new Vacancy();
                u.setID(ri.getInt("id"));
                u.setName(ri.getString("NAME"));
                u.setDescription(ri.getString("DESCRIPTION"));
                u.setDate(ri.getDate("CREATEDON"));
                u.setStartupID(ri.getInt("STARTUPID"));
                li.add(u);
            }
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "contact the administrator");
        } finally {
            statement.close();
        }
        return li;
    }

    public Vacancy getVacancyByName(String name) throws DBManagerException {
        if (name == null) {
    		throw new DBManagerException("Passed parameter <name> is null or empty"
    				+ " Can't proccess the request!");
    	}
    	Statement statement = null;
    	Vacancy v;
    	ResultIterator ri  = null;
    	String query  = "SELECT * " +
                        "FROM VACANCY v " +
                        "WHERE v.name = ?";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, (name));
            ri = statement.executeQuery();
            v = new Vacancy();
            v.setID(ri.getInt("id"));
            v.setName(ri.getString("NAME"));
            v.setDescription(ri.getString("DESCRIPTION"));
            v.setDate(ri.getDate("CREATEDON"));
            v.setStartupID(ri.getInt("STARTUPID"));
        } catch (DBManagerException exc) {
            throw new DBManagerException ("An error occured, " +
                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return v;
    }

    public int createNewVacancy (Vacancy vacancy) throws  DBManagerException{
    Statement statement = null;
    	String query  = "INSERT INTO VACANCY " +
                        "(ID, NAME, DESCRIPTION, " +
                        "STURTUPID, DATE) " +
                        "VALUES " +
                        "(VACANCY_ID.nexval, " +
                        "?, ?, ?, " +
                        "?)";
        try {
            statement = dbManager.prepareStatement(query);
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getDescription());
            statement.setInt(3, vacancy.getStartupId());
            statement.setDate(4, vacancy.getDate());
            statement.executeUpdate();
//         } catch (DBManagerException exc) {
//            throw new DBManagerException ("An error occured, " +
//                            "pleaase, contact an administrator");
        } finally {
            statement.close();
        }
        return (Integer) statement.getGeneratedPrimaryKey();
    }




}
