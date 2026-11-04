import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main (String [] args){
        try{
            double initialBalance = 50000.0;
            BankAccount account = new BankAccountImpl(initialBalance);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankAccountService", account);
            System.out.println("SERVER ngan hang da san sang!!!");
            System.out.println("So du ban dau cua tai khoan:" + initialBalance);

        } catch (Exception e){
            System.out.println("Loi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}