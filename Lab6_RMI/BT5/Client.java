import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối tới RMI Registry trên localhost, cổng 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Tra cứu dịch vụ
            ChatService chatService = (ChatService) registry.lookup("ChatService");
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap ten hien thi cua ban trong phong chat: ");
            String username = scanner.nextLine();
            
            System.out.println("\n--- DÃ VAO PHONG CHAT ---");
            System.out.println("Nhap noi dung tin nhan và nhan Enter de gui.");
            System.out.println("Go lenh 'exit' de thoat.\n");

            // Luồng phụ (hoặc vòng lặp) để gửi tin nhắn
            while (true) {
                System.out.print(username + ": ");
                String message = scanner.nextLine();
                
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Da thoat phong chat.");
                    break;
                }
                
                if (!message.trim().isEmpty()) {
                    chatService.sendMessage(username, message);
                    
                    // Lấy và hiển thị toàn bộ lịch sử chat cập nhật mới nhất từ Server
                    System.out.println("\n--- LICH SU TRO CHUYEN ---");
                    System.out.print(chatService.getChatHistory());
                    System.out.println("--------------------");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Lỗi Client: " + e.toString());
            e.printStackTrace();
        }
    }
}