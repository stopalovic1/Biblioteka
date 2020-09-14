package org.Biblioteka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BookSelectingController {
    private Book book;
    private LibraryDAO dao;
    ObservableList<Book> BookList;
    public TableView<Book> tableViewBooks;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colBookAuthor;
    public TableColumn colBookIsbn;
    public TableColumn colBookYearOfIssue;
    public TableColumn colBookNumberOfPages;
    public TableColumn colBookDateOfPurchase;
    public TableColumn colBookPrice;
    public TableColumn colBookNumberOfSamples;

    public BookSelectingController() {
        dao = LibraryDAO.getInstance();
        BookList = FXCollections.observableArrayList(dao.books());
    }

    public Book getBook() {
        return book;
    }

    @FXML
    public void initialize() {
        tableViewBooks.setItems(BookList);
        colBookId.setCellValueFactory(new PropertyValueFactory("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory("bookName"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory("bookAuthor"));
        colBookIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));
        colBookYearOfIssue.setCellValueFactory(new PropertyValueFactory("yearOfIssue"));
        colBookNumberOfPages.setCellValueFactory(new PropertyValueFactory("numberOfPages"));
        colBookDateOfPurchase.setCellValueFactory(new PropertyValueFactory("dateOfPurchase"));
        colBookPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colBookNumberOfSamples.setCellValueFactory(new PropertyValueFactory("numberOfSamples"));
    }

    public void bookIssueOk(ActionEvent actionEvent)
    {
        book=tableViewBooks.getSelectionModel().getSelectedItem();
        if(book==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda brisanja");
            alert.setHeaderText("Brisanje clana ");
            //alert.setContentText("Da li ste sigurni da želite obrisati citalaca "+reader.getName()+" "+reader.getSurName()+"?");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else
        {
            Stage stage=(Stage) tableViewBooks.getScene().getWindow();
            stage.close();
        }

    }
    public void bookIssueClose(ActionEvent actionEvent)
    {
        book=null;
        Stage stage=(Stage) tableViewBooks.getScene().getWindow();
        stage.close();
    }
}

