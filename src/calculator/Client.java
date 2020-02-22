package calculator;

import calculator.ui.CalculatorController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client extends Thread {

    private String hostname = "localhost";
    private int port = 5555;
    private boolean active = false;
    private CalculatorController calculatorListener;

    private String operacion = null;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port     = port;
    }

    public Client() {
    }

    public synchronized String getOperacion() {
        while (operacion == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String op = operacion;
        operacion = null;
        return op;
    }

    public synchronized void setOperacion(String operacion) {
        this.operacion = operacion;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostname, port);
            System.out.println("conectando al servidor " + inetSocketAddress);
            clientSocket.connect(inetSocketAddress);
            System.out.println("conectado exitosamente" + inetSocketAddress);
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            active = true;
            while (active) {
                dataOutputStream.writeUTF(getOperacion());              //1 - Out
                int resultado = dataInputStream.readInt();                  //2 - In
                calculatorListener.resultadoObtenido(resultado);
                System.out.println("resultado recibido " + resultado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setCalculatorListener(CalculatorController calculatorController) {
        this.calculatorListener = calculatorController;
    }
}
