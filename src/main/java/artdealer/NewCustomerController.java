package artdealer;

import java.io.IOException;
import javafx.fxml.FXML;

public class NewCustomerController {

    @FXML
    private void switchToHomePg() throws IOException {
        App.setRoot("home");
    }
    @FXML
    void switchToCustomerPg() throws IOException {
        App.setRoot("customer");
    }
    
}