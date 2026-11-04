import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeService service = (PrimeService) registry.lookup("PrimeService");

            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập số cần kiểm tra: ");
            int n = sc.nextInt();

            if (service.isPrime(n)) {
                System.out.println(n + " là số nguyên tố.");
            } else {
                System.out.println(n + " KHÔNG phải là số nguyên tố.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
