/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package axiom.controllers;

/**
 *
 * @author user
 */
import axiom.dao.VacancyDAO;
import axiom.dao.impl.VacancyDAOImpl;
import axiom.dbmanager.DBManager;
import axiom.dbmanager.DBManagerException;
import axiom.entity.Vacancy;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;


public class VacancyController extends Controller{


    public int createNewVacncy(String name, String description, int startupId, Date date){
        VacancyDAO vacancyDAO = null;
        int vacancyId = 0;
        try{
            if (isInternal) {
                dbManager = new DBManager();
            }
            vacancyDAO = new VacancyDAOImpl(dbManager);
            Vacancy v = new Vacancy();
            v.setName(name);
            v.setDescription(description);
            v.setDate(date);
            v.setStartupID(startupId);

            vacancyId = (Integer) vacancyDAO.createNewVacancy(v);

            if (isInternal) {
                dbManager.commit();
            }

        }catch(DBManagerException ex){
            if (isInternal)
                Logger.getLogger(NewStartupController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (isInternal)
                dbManager.close();
        }
        return vacancyId;
    }

}
