import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ContactService service = (ContactService) registry.lookup("ContactService");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- QUAN LY DANH BA ---");
                System.out.println("1. Them lien he");
                System.out.println("2. Tim kiem lien he");
                System.out.println("3. Xoa lien he");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    System.out.print("Nhap ten: ");
                    String name = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String phone = sc.nextLine();
                    service.addContact(name, phone);
                    System.out.println("Them thanh cong!");
                } else if (choice == 2) {
                    System.out.print("Nhap ten can tim: ");
                    String name = sc.nextLine();
                    System.out.println("Ket qua: " + service.findContact(name));
                } else if (choice == 3) {
                    System.out.print("Nhap ten can xoa: ");
                    String name = sc.nextLine();
                    boolean ok = service.deleteContact(name);
                    System.out.println(ok ? "Da xoa thanh cong!" : "Khong tim thay de xoa!");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
