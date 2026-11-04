import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main (String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FlightService service = (FlightService) registry.lookup("FlightService");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhap ma chuyen bay (vd: VN123): ");
            String f1 = scanner.nextLine();
            int seats = service.availableSeats(f1);
            if (seats == -1) {
                System.out.println("Khong tim thay chuyen bay");
            } else {
                System.out.println("So ghe trong tren chuyen bay " + f1 + "là" + seats);

            }
            System.out.println("Nhap ma chuyen bay can đat: ");
            String f2 = scanner.nextLine();
            System.out.println("Nhap so luong ghe muon dat: ");
            int bookSeats = Interger.parseInt(scanner.nextLine());

            boolean booked = service.bookTicked(f2, bookSeats);
            if (booked) {
                System.out.println("Dat ve thanh cong!!!");

            } else {
                System.out.println("Dat ve that bai (Khong du ghe hoac sai ma chuyen bay)");
            }
            System.out.print("Nhập mã chuyến bay cần hủy: ");
            String f3 = scanner.nextLine();
            System.out.print("Nhập số lượng ghế muốn hủy: ");
            int cancelSeats = Integer.parseInt(scanner.nextLine());
            
            boolean canceled = service.cancelBooking(f3, cancelSeats);
            if (canceled) {
                System.out.println("-> Hủy vé thành công!");
            } else {
                System.out.println("-> Hủy vé thất bại! Kiểm tra lại mã chuyến bay.");
            }
    } catch (Exception e) {
            System.out.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}