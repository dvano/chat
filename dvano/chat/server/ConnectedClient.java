package dvano.net.chat.server;

import java.io.IOException;
import java.net.Socket;

/**
 * Подключаемый клиент.
 * 
 * @author dvano
 */
public final class ConnectedClient {

    private final SocketStream socketStream;
    private final String name;

    /**
     * 
     * @param socket экземпляр класса Socket
     * @param name имя клиента
     * @throws IOException исключение
     */
    public ConnectedClient(final Socket socket, final String name) throws IOException {
        this.socketStream = new SocketStream(socket);
        this.name = name;
    }

    /**
     * Посылает сообщение клиенту.
     * 
     * @param message сообщение
     * @throws IOException исключение
     */
    public void sendMessage(final String message) throws IOException {
        this.socketStream.getDataOutputStream().writeUTF(message);
        this.socketStream.getDataOutputStream().flush();
    }

    /**
     * Посылает сообщение всем подключенным клиентам.
     * 
     * @param clientList экземпляр класса ClientList
     * @param message сообщение
     * @throws IOException исключение
     */
    public void sendMessageAll(final ClientList clientList, final String message) throws IOException {
        for (int i = 0; i < clientList.getClients().length; i++) {
            if (clientList.getClients()[i] != null) {
                clientList.getClients()[i].sendMessage(message);
            }
        }

        System.out.println(message);
    }

    /**
     * Выгоняет клиента из сервера.
     * 
     * @param clientList экземпляр класса ClientList
     * @throws IOException исключение
     */
    public void exit(final ClientList clientList) throws IOException {
        this.sendMessage("exit");

        this.socketStream.close();
        clientList.remove(this);
    }

    /**
     * 
     * @return входящее сообщение
     * @throws IOException исключение
     */
    public String getMessage() throws IOException {
        return this.socketStream.getDataInputStream().readUTF();
    }

    /**
     * 
     * @return имя клиента
     */
    public String getName() {
        return this.name;
    }
}
