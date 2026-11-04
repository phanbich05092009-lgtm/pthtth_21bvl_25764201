import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", new CalculatorServiceImpl());
            System.out.println(">>> Calculator Server đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
