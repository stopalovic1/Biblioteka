package org.Biblioteka;

public class BookIssue {

    private int id;
    private String bookName;
    private String bookAuthor;
    private String isbn;
    private String workerIssued;
    private String dateOfIssuing;
    private String workerRecieved;
    private String dateOfReceiving;

    public BookIssue(int id, String bookName, String bookAuthor, String isbn, String workerIssued, String dateOfIssuing, String workerRecieved, String dateOfReceiving) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.workerIssued = workerIssued;
        this.dateOfIssuing = dateOfIssuing;
        this.workerRecieved = workerRecieved;
        this.dateOfReceiving = dateOfReceiving;
    }

    public BookIssue() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWorkerIssued() {
        return workerIssued;
    }

    public void setWorkerIssued(String workerIssued) {
        this.workerIssued = workerIssued;
    }

    public String getDateOfIssuing() {
        return dateOfIssuing;
    }

    public void setDateOfIssuing(String dateOfIssuing) {
        this.dateOfIssuing = dateOfIssuing;
    }

    public String getWorkerRecieved() {
        return workerRecieved;
    }

    public void setWorkerRecieved(String workerRecieved) {
        this.workerRecieved = workerRecieved;
    }

    public String getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(String dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }
}
