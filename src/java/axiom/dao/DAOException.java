package axiom.dao;

/**
 *
 * @author Nira
 */
public class DAOException extends RuntimeException {

    /**
* Creates a new instance of <code>DAOException</code> without detail message.
*/
    public DAOException() {
    }


    /**
* Constructs an instance of <code>DAOException</code> with the specified detail message.
* @param msg the detail message.
*/
    public DAOException(String msg) {
        super(msg);
    }

    /**
* Constructs an instance of <code>DAOException</code> with the specified detail message
* and inner exception
* @param msg the detail message.
* @param innerException inner exception
*/
    public DAOException(String msg, Exception innerException) {
        super(msg, innerException);
    }
}
