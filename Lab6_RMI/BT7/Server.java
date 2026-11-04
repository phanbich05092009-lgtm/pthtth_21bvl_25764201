import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.security.sasl.SaslException;

public class Server {
    public static void main  (String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry(1099);
            ShapeCalculator calculator = new ShapeCalculatorImpl();
            registry.rebind("ShapeService", calculator);

            System.out.println("Server dang tinh dien tich hình hoc da san sang hoat dong!!!");
            
        }catch(Exception e) {
            System.err.println("Loi server" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}