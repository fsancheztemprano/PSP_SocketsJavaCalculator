package calculator;

import calculator.ui.CalculatorController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Client extends Thread{

    String hostname = "localhost";
    int port = 5555;
    boolean active = false;
    private Socket clientSocket;
    private ArrayBlockingQueue<Operacion> operaciones = new ArrayBlockingQueue<>(10);
    private CalculatorController calculatorController;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public Client() {
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostname,port);
            System.out.println("conectando al servidor " + inetSocketAddress);
            clientSocket.connect(inetSocketAddress);
            System.out.println("conectado exitosamente" + inetSocketAddress);
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            active = true;
            while(active){
                dataInputStream.readBoolean();                              //I1
                Operacion op = operaciones.take();
                int dato1 = op.getNum1();
                char operacion = op.getOperador();
                int dato2 = op.getNum2();
                dataOutputStream.writeInt(dato1);                           //O2
                dataOutputStream.writeChar(operacion);                      //O3
                dataOutputStream.writeInt(dato2);                           //O4
                int resultado = dataInputStream.readInt();                  //I5
                calculatorController.vista.appendText(resultado + "\n");
                System.out.println("resultado recibido " + resultado);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayBlockingQueue<Operacion> getOperaciones() {
        return operaciones;
    }


    public void setCalculatorController(CalculatorController calculatorController) {
        this.calculatorController = calculatorController;
    }
}
