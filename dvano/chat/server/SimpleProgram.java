package dvano.net.chat.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dvano
 */
public final class SimpleProgram {

    private SimpleProgram() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        try {
            final Server server = new Server(Integer.valueOf(args[0]));
            final Thread thread = new Thread(server);
            thread.setDaemon(true);
            thread.start();
            server.update();
        } catch (IOException ex) {
            Logger.getLogger(SimpleProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
