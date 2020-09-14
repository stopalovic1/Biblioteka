package org.Biblioteka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class BooksMenuController {

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
    public BooksMenuController() {
        dao=LibraryDAO.getInstance();
        BookList= FXCollections.observableArrayList(dao.books());
    }
    @FXML
    public void initialize()
    {
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

    public void addBookButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));
            BookController controller = new BookController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Dodaj knjigu");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                Book book = controller.getBook();
                if (book != null) {
                    dao.addBook(book);
                    BookList.setAll(dao.books());
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateSelectedBookButton(ActionEvent actionEvent)
    {
        Book book=tableViewBooks.getSelectionModel().getSelectedItem();
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali knjigu.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Stage stage = new Stage();
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));
                BookController controller = new BookController(book);
                loader.setController(controller);
                root = loader.load();
                stage.setTitle("Azuriranje clana");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
                stage.setOnHiding(event -> {
                    Book newBook = controller.getBook();
                    if (newBook != null) {
                        dao.updateBook(newBook);
                        BookList.setAll(dao.books());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteSelectedBookButton(ActionEvent actionEvent) {
        Book book=tableViewBooks.getSelectionModel().getSelectedItem();
        if(book==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali knjigu.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda brisanja");
            alert.setHeaderText("Brisanje clana ");
            alert.setContentText("Da li ste sigurni da želite obrisati citalaca " + book.getBookName() + " " + book.getBookAuthor() + "?");
            alert.setResizable(false);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deleteBook(book);
                BookList.setAll(dao.books());
            }
        }
    }
    public void exitBooksMenuButton(ActionEvent actionEvent) {
        Stage stage=(Stage) tableViewBooks.getScene().getWindow();
        stage.close();
    }

}
