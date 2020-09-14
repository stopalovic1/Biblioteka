package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookController {
    private Book book;
    private Reader reader;
    public TextField textFieldBookName;
    public TextField textFieldBookAuthor;
    public TextField textFieldIsbn;
    public TextField textFieldYearOfIssue;
    public TextField textFieldNumberOfPages;
    public TextField textFieldDateOfPurchase;
    public TextField textFieldPrice;
    public TextField textFieldNumberOfSamples;
    public Book getBook() {
        return book;
    }

    public BookController(Book book) {
        this.book = book;
    }

    public BookController() {
    }
    @FXML
    public void initialize()
    {
        if(book!=null)
        {
            textFieldBookName.setText(book.getBookName());
            textFieldBookAuthor.setText(book.getBookAuthor());
            textFieldIsbn.setText(book.getIsbn());
            textFieldYearOfIssue.setText(String.valueOf(book.getYearOfIssue()));
            textFieldNumberOfPages.setText(String.valueOf(book.getNumberOfPages()));
            textFieldDateOfPurchase.setText(book.getDateOfPurchase());
            textFieldPrice.setText(String.valueOf(book.getPrice()));
            textFieldNumberOfSamples.setText(String.valueOf(book.getNumberOfSamples()));
        }
    }


    public void addBookOk(ActionEvent actionEvent)
    {
        if(book==null) book= new Book();
        book.setBookName(textFieldBookName.getText());
        book.setBookAuthor(textFieldBookAuthor.getText());
        book.setIsbn(textFieldIsbn.getText());
        book.setYearOfIssue(Integer.parseInt(textFieldYearOfIssue.getText()));
        book.setNumberOfPages(Integer.parseInt(textFieldNumberOfPages.getText()));
        book.setDateOfPurchase(textFieldDateOfPurchase.getText());
        book.setPrice(Double.parseDouble(textFieldPrice.getText()));
        book.setNumberOfSamples(Integer.parseInt(textFieldNumberOfSamples.getText()));
        Stage stage=(Stage) textFieldBookName.getScene().getWindow();
        stage.close();
    }
    public void addBookCancel(ActionEvent actionEvent)
    {
        book=null;
        Stage stage=(Stage) textFieldBookName.getScene().getWindow();
        stage.close();
    }
}
