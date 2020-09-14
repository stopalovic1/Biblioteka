package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReaderController {

    private Reader reader;
    public TextField textFieldName;
    public TextField textFieldSurname;
    public TextField textFieldJmbg;
    public TextField textFieldDateOfBirth;
    public TextField textFieldNoOfId;
    public TextField textFieldAdress;
    public TextField textFieldPhoneNumber;

    public Reader getReader() {
        return reader;
    }

    public ReaderController() {
    }

    public ReaderController(Reader reader) {
        this.reader = reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @FXML
    public void initialize()
    {
        if(reader!=null)
        {
            textFieldName.setText(reader.getName());
            textFieldSurname.setText(reader.getSurName());
            textFieldJmbg.setText(reader.getJmbg());
            textFieldDateOfBirth.setText(reader.getDateOfBirth());
            textFieldNoOfId.setText(reader.getNoOfId());
            textFieldAdress.setText(reader.getAdress());
            textFieldPhoneNumber.setText(reader.getPhoneNumber());
        }
    }

    public void addReaderOk(ActionEvent actionEvent) {
        if (reader == null) reader = new Reader();
        reader.setName(textFieldName.getText());
        reader.setSurName(textFieldSurname.getText());
        reader.setJmbg(textFieldJmbg.getText());
        reader.setDateOfBirth(textFieldDateOfBirth.getText());
        reader.setNoOfId(textFieldNoOfId.getText());
        reader.setAdress(textFieldAdress.getText());
        reader.setPhoneNumber(textFieldPhoneNumber.getText());
        Stage stage = (Stage) textFieldName.getScene().getWindow();
        stage.close();
    }
    public void addReaderCancel(ActionEvent actionEvent)
    {
        reader=null;
        Stage stage = (Stage) textFieldName.getScene().getWindow();
        stage.close();
    }
}
