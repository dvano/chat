package dvano.chat.client;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для работы с клиентом.
 *
 * @author dvano
 */
public final class ConnectedClientRunnable implements Runnable {

    private final ConnectedClient connectedClient;

    /**
     *
     * @param connectedClient экземпляр класса ConnectedClient
     */
    public ConnectedClientRunnable(ConnectedClient connectedClient) {
        this.connectedClient = connectedClient;
    }

    @Override
    public void run() {
        final Scanner scanner = new Scanner(System.in);

        try {
            // посылает имя клиента серверу.
            this.connectedClient.sendMessage(this.connectedClient.getName());

            while (true) {
                final String message = scanner.nextLine();

                // отправка сообщения серверу.
                this.connectedClient.sendMessage(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClientRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        try {
            try {
                while (true) {
                    // получение сообщения от сервера.
                    final String message = this.connectedClient.getMessage();

                    if (message.equals("exit")) {
                        // остановить цикл.
                        break;
                    }

                    System.out.println(message);
                }
            } finally {
                this.connectedClient.exit(); // отключиться.
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClientRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
