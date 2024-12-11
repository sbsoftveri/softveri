/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import db.DBConnection;
import domain.AbstractDomainObject;
import java.sql.SQLException;

/**
 *
 * @author Stefan
 */
public abstract class AbstractSO {
    
    
    protected abstract void validate(Object obj) throws Exception;
    protected abstract void execute(Object obj) throws Exception;

    public void templateExecute(Object obj) throws Exception {
        try {
            validate(obj);
            execute(obj);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException, Exception {
        DBConnection.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException, Exception {
        DBConnection.getInstance().getConnection().rollback();
    }
   
}
