package calculator.ui;

import calculator.Client;
import java.lang.reflect.MalformedParametersException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CalculatorController extends VBox {

    Client client;

    @FXML
    public TextField fieldNumero1;
    @FXML
    public TextField fieldOperador;
    @FXML
    public TextField fieldNumero2;
    @FXML
    public TextArea textAreaResultados;

    @FXML
    void initialize() {
        client = new Client();
        client.setCalculatorListener(this);
        client.start();
    }

    @FXML
    public void buttonEnviarAction() {
        try {
            int num1 = Integer.parseInt(fieldNumero1.getText());
            int num2 = Integer.parseInt(fieldNumero2.getText());
            char oper = fieldOperador.getText().charAt(0);
            if (oper != '+' && oper != '-' && oper != '*' && oper != '/')
                throw new MalformedParametersException();
            String operacion = "" + num1 + oper + num2;
            client.setOperacion(operacion);
        } catch (NumberFormatException e) {
            System.out.println("error en numeros");
        } catch (MalformedParametersException e) {
            System.out.println("error en operador");
        }
    }

    public void resultadoObtenido(int resultado) {
        textAreaResultados.appendText(resultado + "\n");
    }
}

