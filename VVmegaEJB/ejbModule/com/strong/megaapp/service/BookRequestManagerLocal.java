package com.strong.megaapp.service;

import java.util.List;

import javax.ejb.Local;

import com.strong.megaapp.model.BookRequest;
import com.strong.megaapp.service.exception.BookRequestAlreadyExists;
import com.strong.megaapp.service.exception.BookRequestNotFound;


@Local
public interface BookRequestManagerLocal {
    public BookRequest sendBookRequest(BookRequest bookRequest) throws BookRequestAlreadyExists;    
    public void approveBookRequest(Integer bookRequestNumber) throws BookRequestNotFound;    
    public void rejectBookRequest(Integer bookRequestNumber) throws BookRequestNotFound;    

    public List<BookRequest> viewRequests(String userName, int status);   
}
