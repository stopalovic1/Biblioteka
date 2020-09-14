package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public TextField userTextField;
    public PasswordField passField;
    private final LibraryDAO dao;
    public LoginController()
    {
        dao=LibraryDAO.getInstance();
    }
    public void LogInButton(ActionEvent actionEvent) throws IOException{
        String username = userTextField.getText();
        String password = passField.getText();
        Worker worker=dao.LoggedInWorker(username,password);
        if(worker==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Greska");
            alert.setHeaderText("Greska!");
            alert.setContentText("Unijeli ste pogrešan username ili password!");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/mainMenu.fxml"));
            MainMenuController controller=new MainMenuController(worker);
            loader.setController(controller);
            Parent root=loader.load();
            Scene scene=new Scene(root,590,340);
            Stage stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Glavni meni");
            stage.setScene(scene);
            stage.show();
        }

    }
    public void ExitButton(ActionEvent actionEvent)
    {
        Stage stage= (Stage) userTextField.getScene().getWindow();
        stage.close();
    }
}
