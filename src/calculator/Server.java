package calculator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {

    private String hostname = "localhost";
    private int port = 5555;
    private boolean active = false;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public Server(String hostname, int port) {
        this.hostname = hostname;
        this.port     = port;
    }

    public Server() {
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostname, port);
            serverSocket.bind(inetSocketAddress);
            System.out.println("bind correcto " + inetSocketAddress);
            active = true;
            while (active) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    ServerWorker serverWorker = new ServerWorker(clientSocket);
                    executorService.submit(serverWorker);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
