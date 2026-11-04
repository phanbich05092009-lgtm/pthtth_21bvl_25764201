import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void  main (String [] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankAccount account = (BankAccount) registry.lookup("BankAccountService");
            Scanner scanner = new Scanner(System.in);
            double balance = account.getBalance();
            System.out.println("So du hien tai cua ban la:" + balance+ "VND");

            System.out.print("Nhap so tien ban muon gui:" );
            double depAmount = Double.parseDouble(scanner.nextLine());
            account.deposit(depAmount);
            double newbalance = account.getBalance();
            System.out.println("So du tai khoan cua ban la: "+ newbalance + "VND");

            System.out.print("Nhap so tien ban muon rut: ");
            double withAmount = Double.parseDouble(scanner.nextLine());
            boolean success = account.withdraw (withAmount);
            double sucbalance = account.getBalance();
            if (success) {
                System.out.println("Rut tien thanh cong!!! So du cua ban la: " + sucbalance);
            } else {
                System.out.println("Rut tien that bai!!");
            }
         scanner.close();
        } catch (Exception e) {
            System.err.println("Lỗi kết nối Client: " + e.getMessage());
            e.printStackTrace();   
        }
    }
    
}