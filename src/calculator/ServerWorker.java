package calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerWorker implements Runnable {

    private boolean active = false;
    private final Socket clientSocket;

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
                String operacion = dataInputStream.readUTF();                   //1 - In
                System.out.println("Calculo Recibido " + operacion);
                int resultado = calcular(operacion);
                System.out.println("Calculo Resuelto: " + resultado);
                dataOutputStream.writeInt(resultado);                        //2 - Out
                System.out.println("Respuesta Enviada " + resultado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            //Evitamos que un error en el cliente/worker propague el error al servidor
            e.printStackTrace();
        }
    }


    private static int calcular(String operacion) {
        int result = 0;
        char operador = obtenerOperador(operacion);
        int opIndex = operacion.indexOf(operador);
        int num1 = Integer.parseInt(operacion.substring(0, opIndex));
        int num2 = Integer.parseInt(operacion.substring(opIndex + 1));
        switch (operador) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }

    private static char obtenerOperador(String operacion) {
        for (char s : operacion.toCharArray()) {
            if (s == '+' || s == '-' || s == '*' || s == '/')
                return s;
        }
        return '.';
    }
}
