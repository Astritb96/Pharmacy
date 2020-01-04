/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.UserSession;
import java.util.List;

/**
 *
 * @author dorron
 */
public interface UserSessionInterface {
    void create(UserSession us)     throws UserSessionException;
    void edit(UserSession us)       throws UserSessionException;
    void remove(UserSession us)     throws UserSessionException;
    List<UserSession> findAll();
    UserSession findById(int i)    throws UserSessionException;
}
