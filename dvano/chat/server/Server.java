package dvano.net.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс сервера.
 *
 * @author dvano
 */
public final class Server implements Runnable {

    private final ClientList clientList = new ClientList(6);
    private final ServerSocket server;

    /**
     *
     * @param port порт
     * @throws IOException исключение
     */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Socket socket = this.server.accept(); // принятие сокета.
                final ConnectedRunnable connectedRunnable = new ConnectedRunnable(this.clientList, socket); // экземпляр класса ConnectedRunnable
                final Thread thread = new Thread(connectedRunnable); // экземпляр класса Thread.
                thread.start(); // запустить поток.
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Метод для работы с консолью сервера.
     *
     * @throws IOException исключение
     */
    public void update() throws IOException {
        try {
            final Scanner scanner = new Scanner(System.in);

            while (true) {
                if (scanner.next().equals("stop-server")) {
                    // остановить цикл.
                    break;
                }
            }
        } finally {
            // послать всех клиентов.
            for (int i = 0; i < this.clientList.getClients().length; i++) {
                if (this.clientList.getClients()[i] != null) {
                    this.clientList.getClients()[i].exit(this.clientList);
                }
            }

            // закрыть сервер.
            this.server.close();
        }
    }

    /**
     *
     * @return сокет-сервер
     */
    public ServerSocket getServerSocket() {
        return this.server;
    }
}
