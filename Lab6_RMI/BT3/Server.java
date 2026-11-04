import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeService", new PrimeServiceImpl());
            System.out.println(">>> Prime Server đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
