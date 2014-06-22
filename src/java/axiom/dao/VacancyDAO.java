package axiom.dao;

import axiom.entity.Vacancy;
import axiom.dbmanager.DBManagerException;

import java.util.List;

/**
 *
 * @author Nira
 */
public interface VacancyDAO extends GenericDAO<Vacancy>{

    public List<Vacancy> getVacanciesBySkill(int skillID, int offset,
            int numberOfRecords) throws DBManagerException;

    public List<Vacancy> getVacancyByStartup(int startupID, int offset,
            int numberOfRecords) throws DBManagerException;

    public Vacancy getVacancyByName(String name) throws DBManagerException;
}
