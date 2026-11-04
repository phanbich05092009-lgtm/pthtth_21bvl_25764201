import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo RMI Registry tại cổng 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Khởi tạo đối tượng Chat
            ChatService chatService = new ChatServiceImpl();
            
            // Đăng ký tên dịch vụ
            registry.rebind("ChatService", chatService);
            
            System.out.println("=== RMI CHAT SERVER DA SAN SANG!!! ===");
        } catch (Exception e) {
            System.err.println("Loi Server: " + e.toString());
            e.printStackTrace();
        }
    }
}