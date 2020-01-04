/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.UserSession;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author dorron
 */
public class UserSessionRepository extends EntMngClass implements UserSessionInterface{
    
    /**
     * Kjo ben ruajten e session-it ne databaze
     * ne tabelen user_session kthen  UserSessionException
     * nese ekziston sessioni ose kan ndjonje gabim 
     * gjate transakisonit
     * @param us
     * @throws UserSessionException 
     */
    @Override
    public void create(UserSession us) throws UserSessionException {
        try{
            em.getTransaction().begin();
            em.persist(us);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("2627")){
                throw new UserSessionException("Create Error: Session-i user-it ekziston");
            }else{
                throw new UserSessionException("Create Error: "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }
    /**
     * Kjo ben editimi e session-it ne databaze
     * ne tabelen user_session kthen  UserSessionException
     * nese ekziston sessioni ose kan ndjonje gabim 
     * gjate transakisonit
     * @param us
     * @throws UserSessionException 
     */
    @Override
    public void edit(UserSession us) throws UserSessionException {
         try{
            em.getTransaction().begin();
            em.merge(us);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("2627")){
                throw new UserSessionException("Edit Error: Session-i user-it ekziston");
            }else{
                throw new UserSessionException("Edit Error: "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }
    
     /**
     * Kjo ben fshrijen e session-it ne databaze
     * ne tabelen user_session kthen  UserSessionException
     * nese eshte perdorim sessioni ose kan ndjonje gabim 
     * gjate transakisonit
     * @param us
     * @throws UserSessionException 
     */
    @Override
    public void remove(UserSession us) throws UserSessionException {
        try{
            em.getTransaction().begin();
            UserSession uss = em.merge(us);
            em.remove(uss);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("547")){
                throw new UserSessionException("Remove Error: Session-i user-it është në përdorim");
            }else{
                throw new UserSessionException("Remove Error: "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }
    
    /**
     * Kjo kthen te gjitha sessionet si list
     * @return 
     */
    @Override
    public List<UserSession> findAll() {
        return em.createNamedQuery("UserSession.findAll").getResultList();
    }
    /**
     * Kjo sessioni si pas id , kthen UserSessionException 
     * nese sessioni nuk ekziston
     * @param i
     * @return
     * @throws UserSessionException 
     */
    @Override
    public UserSession findById(int i) throws UserSessionException {
       Query query = em.createQuery("SELECT k FROM UserSession k WHERE k.iDStafi = :id");
       query.setParameter("id", i);
       try{
           return (UserSession)query.getSingleResult();
       }catch(NoResultException nre){
           throw new UserSessionException("FindBy Error: Nuk u gjend sessioni");
       }
    }
    
    
    public boolean isLoggedIn(UserSession us){
        Query query = em.createQuery("SELECT k FROM UserSession k WHERE k.iDStafi = :id");
        query.setParameter("id", us.getIDStafi());
        if(query.getSingleResult() != null){
            return true;
        }
        return false;
    }
    
}
