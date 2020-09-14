package org.Biblioteka;

public class Book {
    private int id;
    private String bookName;
    private String bookAuthor;
    private String isbn;
    private int yearOfIssue;
    private int numberOfPages;
    private String dateOfPurchase;
    private double price;
    private int numberOfSamples;

    public Book(int id, String bookName, String bookAuthor, String isbn, int yearOfIssue, int numberOfPages, String dateOfPurchase, double price, int numberOfSamples) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.yearOfIssue = yearOfIssue;
        this.numberOfPages = numberOfPages;
        this.dateOfPurchase = dateOfPurchase;
        this.price = price;
        this.numberOfSamples=numberOfSamples;
    }

    public Book() {
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

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(int numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }
}
