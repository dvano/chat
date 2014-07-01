package dvano.chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Класс Client.
 * 
 * @author dvano
 */
public final class Client {

    private final Socket socket;
    private final ConnectedClientRunnable connectedClientRunnable;

    /**
     * 
     * @param name имя клиента
     * @param ip IP-адрес сервера
     * @param port порт сервера
     * @throws UnknownHostException исключение
     * @throws IOException исключение
     */
    public Client(String name, String ip, int port) throws UnknownHostException, IOException {
        this.socket = new Socket(InetAddress.getByName(ip), port);
        this.connectedClientRunnable = new ConnectedClientRunnable(new ConnectedClient(this.socket, name));
    }

    /**
     * Метод запускает поток для работы с клиентом.
     */
    public void start() {
        final Thread thread = new Thread(this.connectedClientRunnable);
        thread.setDaemon(true);
        thread.start();
        this.connectedClientRunnable.update();
    }

    /**
     * 
     * @return сокет
     */
    public Socket getSocket() {
        return this.socket;
    }
}
