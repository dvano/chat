package dvano.chat.client;

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
    public ConnectedClient(Socket socket, String name) throws IOException {
        this.socketStream = new SocketStream(socket);
        this.name = name;
    }

    /**
     * Метод посылает сообщение серверу.
     * 
     * @param message сообщение
     * @throws IOException исключение
     */
    public void sendMessage(String message) throws IOException {
        this.socketStream.getDataOutputStream().writeUTF(message);
        this.socketStream.getDataOutputStream().flush();
    }

    /**
     * Метод для закрытия связи с сокетом.
     * 
     * @throws IOException исключение
     */
    public void exit() throws IOException {
        this.socketStream.close();
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
