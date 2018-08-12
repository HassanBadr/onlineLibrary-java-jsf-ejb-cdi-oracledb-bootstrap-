package com.strong.megaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.strong.megaapp.model.Constants;
import com.strong.megaapp.model.MegaUser;
import com.strong.megaapp.model.UserGroup;
import com.strong.megaapp.service.exception.UserAlreadyExists;
import com.strong.megaapp.service.exception.UserNotFound;
import com.strong.megaapp.service.util.AuthenticationUtils;


@Stateless
public class MegaUserManager implements MegaUserManagerLocal {
    
    @PersistenceContext(unitName = "MegaEJB")
    EntityManager em;  
    
    @Override
    public MegaUser getMegaUser(String userID) throws UserNotFound {
        Query query = em.createQuery("select megaUser.id, megaUser.firstName"
                                     + ", megaUser.lastName from MegaUser megaUser where "
                                     + "megaUser.id = :id");
        
        query.setParameter("id", userID);
    
        Object[] megaUserInfo;
        
        try {
            megaUserInfo = (Object[]) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new UserNotFound(exception.getMessage());
        }
        
        MegaUser megaUser = new MegaUser(
                (String) megaUserInfo[0],
                (String) megaUserInfo[1], 
                (String) megaUserInfo[2], 
                null);
        
        return megaUser;        
    }

    @Override
    public MegaUser registerMegaUser(MegaUser user) throws UserAlreadyExists {
    	
    	try {
    		user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("encodeSHA256>>error");
		}
    	
        Query query = em.createQuery("select megaUser from MegaUser megaUser where "
                    + "megaUser.id = :userID");
        
        query.setParameter("userID", user.getId());

        try {
            query.getSingleResult();
            throw new UserAlreadyExists();
        } catch (NoResultException exception) {
            Logger.getLogger(BookManager.class.getName()).log(Level.FINER, "No user found");
        }
        
        List<UserGroup> userGroups = new ArrayList<UserGroup>();        
        
        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(user);
        userGroup.setGroupId(Constants.USER_GROUP);
        
        userGroups.add(userGroup);
        
        user.setUserGroupList(userGroups);
        
        em.persist(user);  
        em.flush();        
        
        return user;
    }

    @Override
    public void removeMegaUser(String userID) throws UserNotFound {
        MegaUser megaUser = em.find(MegaUser.class, userID);

        if (megaUser == null) {
            throw new UserNotFound();
        }
        
        em.remove(megaUser);
        em.flush();
    }

    @Override
    public List<MegaUser> retrieveMegaUsers() {
        Query query = em.createQuery("select megaUser from MegaUser megaUser", MegaUser.class);

        List<MegaUser> result = query.getResultList();        
        
        if (result == null) {
            return new ArrayList<MegaUser>();
        }
        
        return result;
    }

}
