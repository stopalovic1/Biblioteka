package org.Biblioteka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WorkerController {


        private Worker worker;
        public TextField textFieldName;
        public TextField textFieldSurname;
        public TextField textFieldJmbg;
        public TextField textFieldDateOfBirth;
        public TextField textFieldNoOfId;
        public TextField textFieldAdress;
        public TextField textFieldPhoneNumber;
        public TextField textFieldUsername;
        public TextField textFieldPassword;
        public Worker getWorker() {
            return worker;
        }

        public WorkerController() {
        }

        public WorkerController(Worker worker) {
            this.worker = worker;
        }

        public void setWorker(Worker worker) {
            this.worker = worker;
        }

        @FXML
        public void initialize()
        {
            if(worker!=null)
            {
                textFieldName.setText(worker.getName());
                textFieldSurname.setText(worker.getSurName());
                textFieldJmbg.setText(worker.getJmbg());
                textFieldDateOfBirth.setText(worker.getDateOfBirth());
                textFieldNoOfId.setText(worker.getNoOfId());
                textFieldAdress.setText(worker.getAdress());
                textFieldPhoneNumber.setText(worker.getPhoneNumber());
                textFieldUsername.setText(worker.getUsername());
                textFieldPassword.setText(worker.getPassword());
            }
        }

        public void addWorkerOk(ActionEvent actionEvent) {
            if (worker == null) worker = new Worker();
            worker.setName(textFieldName.getText());
            worker.setSurName(textFieldSurname.getText());
            worker.setJmbg(textFieldJmbg.getText());
            worker.setDateOfBirth(textFieldDateOfBirth.getText());
            worker.setNoOfId(textFieldNoOfId.getText());
            worker.setAdress(textFieldAdress.getText());
            worker.setPhoneNumber(textFieldPhoneNumber.getText());
            worker.setUsername(textFieldUsername.getText());
            worker.setPassword(textFieldPassword.getText());
            Stage stage = (Stage) textFieldName.getScene().getWindow();
            stage.close();
        }
        public void addWorkerCancel(ActionEvent actionEvent)
        {
            worker=null;
            Stage stage = (Stage) textFieldName.getScene().getWindow();
            stage.close();
        }


}
