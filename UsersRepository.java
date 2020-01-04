/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.Stafi;
import BL.Users;
import JBcrypt.BCrypt;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author dorron
 */
public class UsersRepository extends EntMngClass implements UsersInterface {

    /**
     * Kjo ben regjistrimin e perdoruesit ne databaze
     * ne tabelen users ,  kthen UsersException nese 
     * ekziston apo ka ndonje gabim gjat transaksionit.
     * @param u
     * @throws UsersException 
     */
    @Override
    public void create(Users u) throws UsersException {
        try{
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("2627")){
                throw new UsersException("Create Error: Përdoruesi ekziston!");
            }else{
                throw new  UsersException("Create Error: "+thro.getClass()+" - "+thro.getMessage()+"!");
            }
        }
    }
    
    /**
     * Kjo ben editim e perdoruesit ne databaze
     * ne tabelen users , kthen UsersException
     * nese ekziston apo ka ndonje gabim 
     * gjat transaksionit.
     * @param u
     * @throws UsersException 
     */
    @Override
    public void edit(Users u) throws UsersException {
        try{
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("2627")){
                throw new UsersException("Edit Error: Përdoruesi ekziston!");
            }else{
                 throw new  UsersException("Edit Error: "+thro.getClass()+" - "+thro.getMessage()+"!");
            }
        }
    }

    @Override
    public void remove(Users u) throws UsersException {
        try{
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("547")){
                throw new UsersException("Remove Error: Përdoruesi është në përdorim!");
            }else{
                throw new  UsersException("Remove Error: "+thro.getClass()+" - "+thro.getMessage()+"!");
            }
        }
    }
    
    /**
     * Kjo kthen te gjithe perdoruesit
     * si list.
     * @return 
     */
    @Override
    public List<Users> findAll() {
       return em.createNamedQuery("Users.findAll").getResultList();
    }

    public Users findById(Stafi id) throws UsersException {
        Query query = em.createQuery("SELECT k FROM Users k WHERE k.stafi = :id");
        query.setParameter("id", id);
        try{
            return (Users)query.getSingleResult();
        }catch(NoResultException nre){
            throw new UsersException("FindById Error: Nuk ekziston ky përdorues");
        }
        
    }
    
    
    public Users logIn(String user , String pass) throws UsersException{
        Query query = em.createQuery("SELECT k FROM Users k WHERE k.username = :username");
        query.setParameter("username", user);
        try{
            Users u = (Users)query.getSingleResult();
            if(BCrypt.checkpw(pass, u.getPasswordi())){
                return u;
            }else{
                throw new UsersException("Nuk e  keni shkurar username apo passwordin e sakt");
            }     
        }catch(NoResultException  nre){
            throw new UsersException("Nuk ekzsiton Perodruesin");
        }
    }

    @Override
    public Users findById(int i) throws UsersException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
