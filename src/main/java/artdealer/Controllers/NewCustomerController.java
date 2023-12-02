package artdealer.Controllers;

import java.io.IOException;
import artdealer.App;
import artdealer.Models.CustomerDTO;
import artdealer.SQL.CreateDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


public class NewCustomerController {
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
	@FXML
	private TextField firstNameEntry;
    @FXML
	private TextField lastNameEntry;
    @FXML
	private TextField emailEntry;
    @FXML
	private TextField phoneEntry;
	@FXML
	private Button myButton;
	
    @FXML
	void submit(ActionEvent event) throws IOException {

        if (firstNameEntry.getText().trim().isEmpty() || lastNameEntry.getText().trim().isEmpty()
            || emailEntry.getText().trim().isEmpty() || phoneEntry.getText().trim().isEmpty()) {
            
            if (firstNameEntry.getText().trim().isEmpty()){
                fNameLabel.setTextFill(Color.color(1, 0, 0));
                fNameLabel.setText("Enter First Name!");
            }
            if (lastNameEntry.getText().trim().isEmpty()){
                lNameLabel.setTextFill(Color.color(1, 0, 0));
                lNameLabel.setText("Enter Last Name!");
            }
            if (emailEntry.getText().trim().isEmpty()){
                emailLabel.setTextFill(Color.color(1, 0, 0));
                emailLabel.setText("Enter Email!");
            }
            if (phoneEntry.getText().trim().isEmpty()){
                phoneLabel.setTextFill(Color.color(1, 0, 0));
                phoneLabel.setText("Enter Phone!");
            }
            if (!phoneEntry.getText().trim().isEmpty()) {
                try {
                int phoneNumber = Integer.parseInt(phoneEntry.getText());
                }
                catch (NumberFormatException e){
                    phoneLabel.setText("Numbers Only!");
                }
            }
        }
        else {
		CustomerDTO newEntry = new CustomerDTO(firstNameEntry.getText(), lastNameEntry.getText(), emailEntry.getText(), phoneEntry.getText());
        CreateDB createDB = new CreateDB();
        createDB.createDB();
        
        
        switchToCustomerRegisteredPg();
        }
	}

    @FXML
    void resetFnameLabel(KeyEvent event) {
        fNameLabel.setText("");
    }

    @FXML
    void resetLnameLabel(KeyEvent event) {
        lNameLabel.setText("");
    }

    @FXML
    void resetEmailLabel(KeyEvent event) {
        emailLabel.setText("");
    }

    @FXML
    void resetPhoneLabel(KeyEvent event) {
        phoneLabel.setText("");
    }

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }

    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }

    @FXML
    private void switchToCustomerRegisteredPg() throws IOException {
        App.setRoot("newCustomerRegistered");
    }
}