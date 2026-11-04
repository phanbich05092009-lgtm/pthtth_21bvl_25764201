import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo đối tượng dịch vụ
            FlightService service = new FlightServiceImpl();

            // Khởi tạo RMI Registry ở cổng mặc định 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Đăng ký dịch vụ với tên "FlightService"
            registry.rebind("FlightService", service);

            System.out.println(">>> Server Đặt Vé Máy Bay đang chạy...");
        } catch (Exception e) {
            System.out.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}