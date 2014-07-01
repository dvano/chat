package dvano.chat.server;

/**
 * Список подключаемых клиентов.
 *
 * @author dvano
 */
public final class ClientList {

    private final ConnectedClient[] clients;

    /**
     *
     * @param size размер массива
     */
    public ClientList(int size) {
        this.clients = new ConnectedClient[size];
    }

    /**
     * Добавляет клиента в список.
     *
     * @param client экземпляр класса ConnectedClient
     */
    public void add(ConnectedClient client) {
        for (int i = 0; i < this.clients.length; i++) {
            if (this.clients[i] == null) {
                this.clients[i] = client;
                break;
            }
        }
    }

    /**
     * Удаляет клиента из списка.
     *
     * @param client экземпляр класса ConnectedClient
     */
    public void remove(ConnectedClient client) {
        for (int i = 0; i < this.clients.length; i++) {
            if (this.clients[i] != null) {
                if (this.clients[i].equals(client)) {
                    this.clients[i] = null;
                }
            }
        }
    }

    /**
     *
     * @return массив подключаемых клиентов
     */
    public ConnectedClient[] getClients() {
        return this.clients;
    }
}
