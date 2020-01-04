/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.Users;
import java.util.List;
/**
 *
 * @author dorron
 */
public interface UsersInterface {
    
    void create(Users u)    throws UsersException;
    void edit(Users u)      throws UsersException;
    void remove(Users u)    throws UsersException;
    List<Users> findAll();
    Users findById(int i)   throws UsersException;
}
