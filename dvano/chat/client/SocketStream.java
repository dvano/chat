package dvano.net.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Класс для работы с сокетом.
 *
 * @author dvano
 */
public final class SocketStream {

    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    /**
     *
     * @param socket экземпляр класса Socket
     * @throws IOException исключение
     */
    public SocketStream(final Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(this.socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
    }

    /**
     * Метод закрывает сокет.
     *
     * @throws IOException исключение
     */
    public void close() throws IOException {
        this.socket.close();
    }

    /**
     *
     * @return входящий поток
     */
    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }

    /**
     *
     * @return исхлдящий поток
     */
    public DataOutputStream getDataOutputStream() {
        return this.dataOutputStream;
    }
}
