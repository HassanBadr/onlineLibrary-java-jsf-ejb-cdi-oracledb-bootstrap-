package com.strong.megaapp.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
    private static final long serialVersionUID = 197654646546456456L;
    
    @Id
    @GeneratedValue(generator="bk_seq")
    @SequenceGenerator(name="bk_seq",sequenceName="BOOK_SEQ", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
    @Column(name = "ISBN")
    private String isbn;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 128, message = "Book title must be between 5 and 128 characters")
    @Column(name = "TITLE")
    private String title;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 128, message = "Book author must be between 3 and 128 characters")
    @Column(name = "AUTHOR")
    private String author;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 64, message = "Book published must be between 3 and 64 characters")
    @Column(name = "PUBLISHER")
    private String publisher;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 64, message = "Book language must be between 3 and 64 characters")
    @Column(name = "LANG")
    private String lang;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "CONTENT")
    private Serializable content;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
    private List<BookRequest> bookRequestList;

    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String isbn, String title, String author, String publisher, String lang, Serializable content) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.lang = lang;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Serializable getContent() {
        return content;
    }

    public void setContent(Serializable content) {
        this.content = content;
    }

    public List<BookRequest> getBookRequestList() {
        return bookRequestList;
    }

    public void setBookRequestList(List<BookRequest> bookRequestList) {
        this.bookRequestList = bookRequestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfprohtml5.megaapp.model.Book[ id=" + id + " ]";
    }
    
}
