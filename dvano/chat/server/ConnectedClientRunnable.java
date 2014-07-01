package dvano.chat.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Поток для работы с подключаемым клиентом.
 *
 * @author dvano
 */
public final class ConnectedClientRunnable implements Runnable {

    private final ConnectedClient connectedClient;
    private final ClientList clientList;

    /**
     *
     * @param connectedClient экземпляр класса ConnectedClient
     * @param clientList экземпляр класса ClientList
     */
    public ConnectedClientRunnable(ConnectedClient connectedClient, ClientList clientList) {
        this.connectedClient = connectedClient;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        try {
            try {
                // уведомляет всех клиентов о новом клиенте.
                this.connectedClient.sendMessageAll(this.clientList, this.connectedClient.getName() + " connected to the server.");

                while (true) {
                    // полючить входящее сообщение.
                    final String message = this.connectedClient.getMessage();

                    // если сообщение еквивалентно exit...
                    if (message.equals("exit")) {
                        // ...послать сообщение клиенту.
                        this.connectedClient.sendMessage("exit");
                        break;
                    }

                    // послать всем подключаемым клиентам входящее сообщение.
                    this.connectedClient.sendMessageAll(this.clientList, this.connectedClient.getName() + ": " + message);
                }
            } finally {
                // выход клиента из сервера.
                this.connectedClient.exit(this.clientList);

                // послать всем клиентам о отключении клиента.
                this.connectedClient.sendMessageAll(this.clientList, this.connectedClient.getName() + " left the server.");
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClientRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
