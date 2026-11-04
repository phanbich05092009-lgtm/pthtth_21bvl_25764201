import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối đến Server ở localhost qua cổng 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            HelloService service = (HelloService) registry.lookup("HelloService");
            
            // Gọi hàm từ xa
            String result = service.sayHello();
            System.out.println("Kết quả từ server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
