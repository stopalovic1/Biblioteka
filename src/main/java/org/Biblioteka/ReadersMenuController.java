package org.Biblioteka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import java.sql.Statement;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ReadersMenuController {

    private LibraryDAO dao;
    ObservableList<Reader> readerList;
    public TableView<Reader> tableViewReaders;
    public TableColumn colReaderId;
    public TableColumn colReaderName;
    public TableColumn colReaderSurname;
    public TableColumn colReaderJmbg;
    public TableColumn colReaderDateOfBirth;
    public TableColumn colReaderNoOfId;
    public TableColumn colReaderAdress;
    public TableColumn colReaderPhoneNumber;
    public ReadersMenuController() {
        dao=LibraryDAO.getInstance();
        readerList=FXCollections.observableArrayList(dao.readers());
    }
    @FXML
    public void initialize()
    {
        tableViewReaders.setItems(readerList);
        colReaderId.setCellValueFactory(new PropertyValueFactory("id"));
        colReaderName.setCellValueFactory(new PropertyValueFactory("name"));
        colReaderSurname.setCellValueFactory(new PropertyValueFactory("surName"));
        colReaderJmbg.setCellValueFactory(new PropertyValueFactory("jmbg"));
        colReaderDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        colReaderNoOfId.setCellValueFactory(new PropertyValueFactory("noOfId"));
        colReaderAdress.setCellValueFactory(new PropertyValueFactory("adress"));
        colReaderPhoneNumber.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
    }

    public void addReaderButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reader.fxml"));
            ReaderController controller = new ReaderController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Dodaj clana");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                Reader reader = controller.getReader();
                if (reader != null) {
                    dao.addReader(reader);
                    readerList.setAll(dao.readers());
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateSelectedReaderButton(ActionEvent actionEvent)
    {
        Reader reader=tableViewReaders.getSelectionModel().getSelectedItem();
        if (reader == null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali citaoca.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Stage stage = new Stage();
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reader.fxml"));
                ReaderController controller = new ReaderController(reader);
                loader.setController(controller);
                root = loader.load();
                stage.setTitle("Azuriranje clana");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
                stage.setOnHiding(event -> {
                    Reader newReader = controller.getReader();
                    if (newReader != null) {
                        dao.updateReader(newReader);
                        readerList.setAll(dao.readers());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteSelectedReaderButton(ActionEvent actionEvent)
    {
        Reader reader=tableViewReaders.getSelectionModel().getSelectedItem();
        if(reader==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali citaoca.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda brisanja");
            alert.setHeaderText("Brisanje clana ");
            alert.setContentText("Da li ste sigurni da želite obrisati citalaca " + reader.getName() + " " + reader.getSurName() + "?");
            alert.setResizable(false);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deleteReader(reader);
                readerList.setAll(dao.readers());
            }
        }
    }
    public void exitReadersMenuButton(ActionEvent actionEvent)
    {
        Stage stage=(Stage) tableViewReaders.getScene().getWindow();
        stage.close();
    }
}
