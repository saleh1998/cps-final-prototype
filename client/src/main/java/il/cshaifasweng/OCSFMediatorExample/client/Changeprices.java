package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Changeprices {

    @FXML
    private Button changebtn;

    @FXML
    private TextField fullmem;

    @FXML
    private TextField inadv;

    @FXML
    private TextField inplace;

    @FXML
    private TextField regmultiple;

    @FXML
    private TextField regsingle;

    @FXML
    void updateDB(ActionEvent event) throws IOException {
        int fullmember = -1;
        if(!fullmem.getText().isEmpty())  fullmember = Integer.parseInt(fullmem.getText());
        int inadva = -1;
        if(!inadv.getText().isEmpty())  inadva = Integer.parseInt(inadv.getText());
        int inpla = -1;
        if(!inplace.getText().isEmpty()) inpla = Integer.parseInt(inplace.getText());
        int regm = -1;
        if(!regmultiple.getText().isEmpty()) regm = Integer.parseInt(regmultiple.getText());
        int regs = -1;
        if(!regsingle.getText().isEmpty()) regs = Integer.parseInt(regsingle.getText());

        int arr[]={fullmember,inadva,inpla,regm,regs};
        Message msg = new Message(arr);
        msg.setMessage("attempt to change data");
        SimpleClient.getClient().sendToServer(msg);







    }

}
