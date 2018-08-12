package com.strong.megaapp.service;

import java.util.List;

import javax.ejb.Local;

import com.strong.megaapp.model.MegaUser;
import com.strong.megaapp.service.exception.UserAlreadyExists;
import com.strong.megaapp.service.exception.UserNotFound;


@Local
public interface MegaUserManagerLocal {
    public MegaUser getMegaUser(String userID) throws UserNotFound;
    public List<MegaUser> retrieveMegaUsers();        
    public MegaUser registerMegaUser(MegaUser user) throws UserAlreadyExists;
    public void removeMegaUser(String userID) throws UserNotFound;    
}
