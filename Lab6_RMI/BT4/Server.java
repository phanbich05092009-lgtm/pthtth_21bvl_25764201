import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ContactService", new ContactServiceImpl());
            System.out.println(">>> Contact Server da mo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
