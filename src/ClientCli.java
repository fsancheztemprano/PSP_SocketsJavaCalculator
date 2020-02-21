import calculator.Client;
import calculator.Server;

public class ClientCli {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
