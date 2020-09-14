package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class ReportChoosingController {

    public RadioButton toggleReaders;
    public RadioButton toggleWorkers;
    public RadioButton toggleBooks;
    public ToggleGroup toggleGroup;
    private LibraryDAO dao;
    public ReportChoosingController() {
        dao = LibraryDAO.getInstance();
    }

    @FXML
    public void initialize()
    {
        toggleGroup=new ToggleGroup();
        toggleReaders.setToggleGroup(toggleGroup);
        toggleWorkers.setToggleGroup(toggleGroup);
        toggleBooks.setToggleGroup(toggleGroup);
    }



    public void reportOkButton(ActionEvent actionEvent) throws JRException {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if(selectedRadioButton.equals(toggleReaders))
        {
            new PrintReports().showReportOfAllReaders(dao.getConnection());
        }
        else if(selectedRadioButton.equals(toggleWorkers))
        {
            new PrintReports().showReportOfAllWorkers(dao.getConnection());
        }
        else if(selectedRadioButton.equals(toggleBooks))
        {
            new PrintReports().showReportOfAllBooks(dao.getConnection());
        }
        Stage stage= (Stage) toggleBooks.getScene().getWindow();
        stage.close();
    }
    public void reportCancelButton(ActionEvent actionEvent)
    {
        Stage stage= (Stage) toggleBooks.getScene().getWindow();
        stage.close();
    }

}
