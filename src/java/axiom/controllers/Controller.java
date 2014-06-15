 package axiom.controllers;

import axiom.dbmanager.DBManager;

/**
 *
 * @author Nira
 */
public class Controller {

    protected DBManager dbManager;

    /**
     *
     */
    protected boolean isInternal;

    /**
     * Default constructor. Used to internal transactions.
     */
    public Controller() {
        isInternal = true;
    }

    /**
     *
     * @param dbManager
     */
    public Controller(DBManager dbManager) {
        this.dbManager = dbManager;
        isInternal = false;
    }

}
