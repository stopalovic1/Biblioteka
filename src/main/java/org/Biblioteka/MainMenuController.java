package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainMenuController {
    private Worker worker;
    private LibraryDAO dao;
    public ImageView mainMenuImageView;
    @FXML
    public Label LoggedInUserLabel;
    public MainMenuController(Worker worker) {
        this.worker = worker;
        dao=LibraryDAO.getInstance();
    }



    @FXML
    public void initialize()
    {
        LoggedInUserLabel.setText(worker.getName()+" "+worker.getSurName());
        Image image=new Image(getClass().getResourceAsStream("/reports/book.jpg"));
        mainMenuImageView.setFitHeight(215);
        mainMenuImageView.setFitWidth(150);
        mainMenuImageView.setImage(image);

    }

    public void viewAllReadersButton(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/allReadersForm.fxml"));
            ReadersMenuController controller = new ReadersMenuController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Citaoci");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void viewAllWorkersButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/allWorkersForm.fxml"));
            WorkersMenuController controller = new WorkersMenuController(worker);
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Radnici");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void viewAllBooksButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/allBooksForm.fxml"));
            BooksMenuController controller = new BooksMenuController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Knjige");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void issueAndReturnBookButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookIssuingForm.fxml"));
            BookIssuingMenuController controller = new BookIssuingMenuController(worker);
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Iznajmljivanje i vracanje knjiga");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void reportButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reportChoosingForm.fxml"));
            ReportChoosingController controller = new ReportChoosingController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Izbor izvjestaja");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void viewInfoButton(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/infoForm.fxml"));
            InfoController controller = new InfoController();
            loader.setController(controller);
            root = loader.load();
            stage.setTitle("Izbor izvjestaja");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exitAppButton(ActionEvent actionEvent)
    {
        System.exit(1);
    }
}
