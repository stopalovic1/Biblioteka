package org.Biblioteka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.time.LocalDate;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class BookIssuingMenuController {

    private Worker worker;
    public Label workerLabel;
    public ComboBox<Reader> readersComboBox;
    public ObservableList<Reader> readerList;
    public ObservableList<BookIssue> bookIssueList;
    public TableView<BookIssue> tableViewIssuedBooks;
    public DatePicker dateOfIssuingPicker;
    public TableColumn colBookIssueId;
    public TableColumn colBookIssueName;
    public TableColumn colBookIssueAuthor;
    public TableColumn colBookIssueIsbn;
    public TableColumn colBookIssueWorkerIssued;
    public TableColumn colBookDateOfIssuing;
    public TableColumn colBookIssueWorkerRecieved;
    public TableColumn colBookIssueDateOfReceiving;
    private LibraryDAO dao;

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public BookIssuingMenuController() {
    }

    public BookIssuingMenuController(Worker worker) {
        this.worker = worker;
        dao=LibraryDAO.getInstance();
        readerList= FXCollections.observableArrayList(dao.readers());

    }
    @FXML
    public void initialize()
    {
        workerLabel.setText(worker.getName()+" "+worker.getSurName());
        dateOfIssuingPicker.setValue(LocalDate.now());
        readersComboBox.setItems(readerList);
        readersComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, reader, t1) -> {
            bookIssueList=FXCollections.observableArrayList(dao.bookIssues(t1));
            tableViewIssuedBooks.setItems(bookIssueList);
            colBookIssueId.setCellValueFactory(new PropertyValueFactory("id"));
            colBookIssueName.setCellValueFactory(new PropertyValueFactory("bookName"));
            colBookIssueAuthor.setCellValueFactory(new PropertyValueFactory("bookAuthor"));
            colBookIssueIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));
            colBookIssueWorkerIssued.setCellValueFactory(new PropertyValueFactory("workerIssued"));
            colBookDateOfIssuing.setCellValueFactory(new PropertyValueFactory("dateOfIssuing"));
            colBookIssueWorkerRecieved.setCellValueFactory(new PropertyValueFactory("workerRecieved"));
            colBookIssueDateOfReceiving.setCellValueFactory(new PropertyValueFactory("dateOfReceiving"));
       });
    }

    public void issueBookButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        Reader reader=readersComboBox.getSelectionModel().getSelectedItem();
        if(reader==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali citaoca.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookSelectingForm.fxml"));
                BookSelectingController controller = new BookSelectingController();
                loader.setController(controller);
                root = loader.load();
                stage.setTitle("Odaberite knjigu");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
                stage.setOnHiding(event -> {
                    Book book=controller.getBook();
                    if(book!=null)
                    {
                        dao.addBookIssue(reader,worker,book,dateOfIssuingPicker.getValue().toString());
                        bookIssueList.setAll(dao.bookIssues(reader));
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void returnBookButton(ActionEvent actionEvent)
    {
        BookIssue bookIssue=tableViewIssuedBooks.getSelectionModel().getSelectedItem();
        Reader reader=readersComboBox.getSelectionModel().getSelectedItem();
        if(bookIssue==null || reader==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali knjigu koju zelite vratiti.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else
        {
            if(bookIssue.getDateOfReceiving()!=null)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Greska");
                alert.setHeaderText("Ova knjiga je vec vracena");
                alert.setContentText("Knjiga"+ " " +bookIssue.getBookName()+" "+"je vec vracena");
                alert.setResizable(false);
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Knjiga vracena");
                alert.setHeaderText("Knjiga uspjesno vracena");
                alert.setContentText("Knjiga"+" "+bookIssue.getBookName()+" "+"je uspjesno vracena." );
                alert.setResizable(false);
                alert.showAndWait();
                dao.updateBookRental(bookIssue,worker,LocalDate.now().toString());
                bookIssueList.setAll(dao.bookIssues(reader));
            }
        }
    }
    public void exitBookIssuingButton(ActionEvent actionEvent)
    {
        Stage stage=(Stage) tableViewIssuedBooks.getScene().getWindow();
        stage.close();
    }
    public void reportOfReaderButton(ActionEvent actionEvent)
    {
        Reader reader=readersComboBox.getSelectionModel().getSelectedItem();
        if(reader==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali citaoca.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            try {
                new PrintReports().showReportOfReader(dao.getConnection(), reader);

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }
}
