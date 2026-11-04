import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Mở cổng 1099 ngay trong code, không cần mở cmd rmiregistry
            Registry registry = LocateRegistry.createRegistry(1099);
            
            HelloService service = new HelloServiceImpl();
            registry.rebind("HelloService", service);
            
            System.out.println(">>> Server đã khởi động và sẵn sàng phục vụ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
