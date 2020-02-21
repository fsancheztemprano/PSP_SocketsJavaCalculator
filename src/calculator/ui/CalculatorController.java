package calculator.ui;

import java.net.URL;
import java.util.ResourceBundle;

import calculator.Client;
import calculator.Operacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

public class CalculatorController extends VBox {

    Client client;

    @FXML
    public TextField n1;
    @FXML
    public TextField op;
    @FXML
    public TextField n2;
    @FXML
    public TextArea vista;
    @FXML
    private TextField operacion;
    @FXML
    void initialize() {
        client = new Client();
        client.setCalculatorController(this);
        client.start();
    }
    @FXML
    public void calcular(ActionEvent actionEvent) {
        int num1 = Integer.parseInt(n1.getText());
        int num2 = Integer.parseInt(n2.getText());
        char oper = op.getText().charAt(0);
        Operacion operacion = new Operacion(num1,num2,oper);
        client.getOperaciones().offer(operacion);
    }
}

