package dvano.chat.client;

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
    public static void main(String[] args) {
        if (args.length != 3) {
            System.exit(1);
        }

        try {
            final Client client = new Client(args[0], args[1], Integer.valueOf(args[2]));
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(SimpleProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
