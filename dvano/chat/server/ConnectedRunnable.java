package dvano.net.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для работы с только что принятым сокетом.
 *
 * @author dvano
 */
public final class ConnectedRunnable implements Runnable {

    private final ClientList clientList;
    private final Socket socket;

    /**
     *
     * @param clientList экземпляр класса ClientList
     * @param socket экземпляр класса Socket
     */
    public ConnectedRunnable(final ClientList clientList, final Socket socket) {
        this.clientList = clientList;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            final SocketStream socketStream = new SocketStream(this.socket); // полючить объект для работы с сокетом.
            final String name = socketStream.getDataInputStream().readUTF(); // получить имя клиента.
            final ConnectedClient connectedClient = new ConnectedClient(this.socket, name); // создать экземпляр класса ConnectedClient.
            this.clientList.add(connectedClient); // добавить клиента в список.
            final ConnectedClientRunnable connectedClientRunnable = new ConnectedClientRunnable(connectedClient, this.clientList); // получить объект для работы с клиентом.
            final Thread thread = new Thread(connectedClientRunnable); // экземпляр класса Thread.
            thread.start(); // запустить поток.
        } catch (IOException ex) {
            Logger.getLogger(ConnectedRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
