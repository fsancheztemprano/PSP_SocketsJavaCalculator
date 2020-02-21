package calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerWorker implements Runnable {

    boolean active = false;
    private Socket clientSocket;


    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            active = true;
            System.out.println("Worker iniciado " + clientSocket.getRemoteSocketAddress());
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            while (active && !clientSocket.isClosed()) {

                dataOutputStream.writeBoolean(true);                                //O1
                int dato1 = dataInputStream.readInt();                                 //I2
                char operador = dataInputStream.readChar();                            //I3
                int dato2 = dataInputStream.readInt();                                 //I4
                System.out.println("calculo recibido " + dato1 + operador + dato2);
                int resultado = Calculadora.calcular(dato1, operador, dato2);
                dataOutputStream.writeInt(resultado);                                   //O5
                System.out.println("Respuesta enviada " + resultado);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
