package com.strong.megaapp.service;

import com.strong.megaapp.model.Book;
import com.strong.megaapp.service.exception.BookAlreadyExists;
import com.strong.megaapp.service.exception.BookNotFound;
import java.util.List;
import javax.ejb.Local;


@Local
public interface BookManagerLocal {   
    public Book getBookInformation(Integer bookID) throws BookNotFound;    
    public Book registerBook(Book book) throws BookAlreadyExists;    
    public Book updateBook(Book book) throws BookNotFound;    
    public void removeBook(Integer bookID) throws BookNotFound;    
    public byte[] getBookContent(Integer bookID) throws BookNotFound;
    public List<Book> getAllBooks(Book book);    
}
