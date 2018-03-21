package com.strong.megaapp.backing;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.strong.megaapp.model.Book;
import com.strong.megaapp.model.BookRequest;
import com.strong.megaapp.model.Constants;
import com.strong.megaapp.model.MegaUser;
import com.strong.megaapp.service.BookManagerLocal;
import com.strong.megaapp.service.BookRequestManagerLocal;
import com.strong.megaapp.service.exception.BookNotFound;
import com.strong.megaapp.service.exception.BookRequestAlreadyExists;

@Named
@ViewScoped
public class BookSearchBacking extends BaseBacking implements Serializable {
    @EJB
    private BookManagerLocal bookManager;  
    
    @EJB
    private BookRequestManagerLocal bookRequestManager;

    private List<Book> bookList;    
    private String searchTitle;
    private String infoMessage;
    private Book selectedBook;
    

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }
    
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }    
    
    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }    
    
    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }    
    
    public String retrieveBookList() {
        Book searchableBook = new Book();
        
        searchableBook.setTitle(searchTitle);
        
        bookList = bookManager.getAllBooks(searchableBook);
        
        if (bookList.isEmpty()) {
            infoMessage = "No Book results found";
        } else {
            infoMessage = bookList.size() + " book(s) found";
        }
        
        return null;
    }
    
    public String requestBookCopy() {
        BookRequest bookRequest = new BookRequest();
        MegaUser megaUser = new MegaUser();
        
        megaUser.setId(getRequest().getUserPrincipal().getName());
        
        bookRequest.setUserId(megaUser);
        bookRequest.setBookId(getSelectedBook());
        
        try {
            bookRequestManager.sendBookRequest(bookRequest);
            infoMessage = "Book request sent";
        } catch (BookRequestAlreadyExists ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, null, ex);
            infoMessage = "You already sent a request for this book";
        } catch (Exception ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("An error occurs while sending book request"));
        }
        
        return null;
    } 
    
    public String deleteBook() {
        try {
            Book currentSelectedBook = getSelectedBook();
            
            bookManager.removeBook(currentSelectedBook.getId());
            bookList.remove(currentSelectedBook);
            
            infoMessage = "Book deleted successfully";
        } catch (BookNotFound ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("An error occurs while deleting the book"));            
        }
        
        return null;
    }    
    
    public String downloadBook() {
        Book currentSelectedBook = getSelectedBook();
        Book book;
        byte[] content;
        
        try {
            book = bookManager.getBookInformation(currentSelectedBook.getId());
            content = bookManager.getBookContent(currentSelectedBook.getId());
        } catch (BookNotFound ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, "No books found !!!", ex);
            return null;
        }        
        
        ExternalContext externalContext = getContext().getExternalContext();

        externalContext.responseReset(); 
        externalContext.setResponseContentType(Constants.APP_PDF_TYPE); 
        externalContext.setResponseContentLength(content.length); 
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + book.getTitle() + ".pdf\""); 
        OutputStream output = null;
        
        try {
            output = externalContext.getResponseOutputStream();
            
            output.write(content);
            
            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            getContext().responseComplete();        
        }
        
        return null;
    }
    public String editBook() {
    	try {
            Book currentSelectedBook = getSelectedBook();
            
            bookManager.updateBook(currentSelectedBook);
            
            infoMessage = "Book Updated successfully";
        } catch (BookNotFound ex) {
            Logger.getLogger(BookSearchBacking.class.getName()).log(Level.SEVERE, null, ex);
            getContext().addMessage(null, new FacesMessage("An error occurs while updating the book"));            
        }
    	
    	return null;
    }
}