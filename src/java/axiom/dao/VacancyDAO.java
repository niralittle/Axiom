package axiom.dao;

import axiom.entity.Vacancy;
import axiom.entity.Skill;
import axiom.entity.Startup;
import axiom.dbmanager.DBManagerException;
import java.util.List;

/**
 *
 * @author Nira
 */
public interface VacancyDAO extends GenericDAO<Vacancy>{

    /**
     * Method for delete occupied Vacancy
     */
    void deleteVacancy(Vacancy vacancy) throws DBManagerException;

    public List <Vacancy> getVacancyBySkill(Skill skill) throws DBManagerException;

    public List <Vacancy> getVacancyByStartup (Startup startup)
                                                throws DBManagerException;

}
