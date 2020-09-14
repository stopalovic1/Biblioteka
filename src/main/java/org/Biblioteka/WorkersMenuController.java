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

public class WorkersMenuController {




    private LibraryDAO dao;
    private Worker worker;
    ObservableList<Worker> workerList;
    public TableView<Worker> tableViewWorkers;
    public TableColumn colWorkerId;
    public TableColumn colWorkerName;
    public TableColumn colWorkerSurname;
    public TableColumn colWorkerJmbg;
    public TableColumn colWorkerDateOfBirth;
    public TableColumn colWorkerNoOfId;
    public TableColumn colWorkerAdress;
    public TableColumn colWorkerPhoneNumber;
    public TableColumn colWorkerUsername;

    public WorkersMenuController() {
        dao=LibraryDAO.getInstance();
        workerList= FXCollections.observableArrayList(dao.workers());
    }

    public WorkersMenuController(Worker worker)
    {
        this.worker=worker;
        dao=LibraryDAO.getInstance();
        workerList= FXCollections.observableArrayList(dao.workers());
    }
    @FXML
    public void initialize()
    {
        tableViewWorkers.setItems(workerList);
        colWorkerId.setCellValueFactory(new PropertyValueFactory("id"));
        colWorkerName.setCellValueFactory(new PropertyValueFactory("name"));
        colWorkerSurname.setCellValueFactory(new PropertyValueFactory("surName"));
        colWorkerJmbg.setCellValueFactory(new PropertyValueFactory("jmbg"));
        colWorkerDateOfBirth.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));
        colWorkerNoOfId.setCellValueFactory(new PropertyValueFactory("noOfId"));
        colWorkerAdress.setCellValueFactory(new PropertyValueFactory("adress"));
        colWorkerPhoneNumber.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colWorkerUsername.setCellValueFactory(new PropertyValueFactory("username"));


    }

    public void addWorkerButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker.fxml"));
            WorkerController controller = new WorkerController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Dodaj radnika");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                Worker worker = controller.getWorker();
                if (worker != null) {
                    dao.addWorker(worker);
                    workerList.setAll(dao.workers());
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateSelectedWorkerButton(ActionEvent actionEvent)
    {
        Worker selectedWorker=tableViewWorkers.getSelectionModel().getSelectedItem();
        if (selectedWorker == null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali radnika.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Stage stage = new Stage();
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker.fxml"));
                WorkerController controller = new WorkerController(selectedWorker);
                loader.setController(controller);
                root = loader.load();
                stage.setTitle("Azuriranje clana");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
                stage.setOnHiding(event -> {
                    Worker newWorker = controller.getWorker();
                    if (newWorker != null) {
                        dao.updateWorker(newWorker);
                        workerList.setAll(dao.workers());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteSelectedWorkerButton(ActionEvent actionEvent)
    {
        Worker selectedWorker=tableViewWorkers.getSelectionModel().getSelectedItem();
        if(selectedWorker==null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Niste odabrali radnika.");
            alert.setResizable(false);
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda brisanja");
            alert.setHeaderText("Brisanje radnika ");
            alert.setContentText("Da li ste sigurni da želite obrisati radnika " + selectedWorker.getName() + " " + selectedWorker.getSurName() + "?");
            alert.setResizable(false);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (selectedWorker.equals(worker)) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Greska");
                    alert1.setHeaderText("Brisanje neuspjelo");
                    alert1.setContentText("Ne mozete obrisati trenutno ulogovanog radnika!");
                    alert1.showAndWait();
                } else {
                    dao.deleteWorker(selectedWorker);
                    workerList.setAll(dao.workers());
                }
            }
        }
    }
    public void exitWorkersMenuButton(ActionEvent actionEvent)
    {
        Stage stage=(Stage) tableViewWorkers.getScene().getWindow();
        stage.close();
    }
}
