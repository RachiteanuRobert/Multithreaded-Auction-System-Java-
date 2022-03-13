import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that reads the input and calls the methods that drive the code
 */
public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();

        System.out.println("---Welcome to the Auction House---");
        System.out.println();
        System.out.println("Please enter a command:");

        facade.makeBrokers();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        label:
        while(true) {
            //Reads the lines
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] lineWords;
            if (line != null) lineWords = line.split(";");
            else lineWords = new String[]{""};

            String command = lineWords[0];
            //Decides which command is given
            switch (command) {
                case "exit":
                case "quit":
                    break label;
                case "addProduct":
                    facade.addProduct(lineWords);
                    break;
                case "addClient":
                    facade.addClient(lineWords);
                    break;
                case "solicitBid":
                    facade.solicitBid(lineWords);
                    break;
                case "list":
                    facade.listCommand(lineWords[1]);
                    break;
                default:
                    System.out.println("Invalid command. Command not found.");
                    break;
            }
        }

    }
}
