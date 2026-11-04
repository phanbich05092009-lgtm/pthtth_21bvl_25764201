import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main (String [] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ShapeCalculator calculator = (ShapeCalculator) registry.lookup("ShapeService");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap chieu rong hinh chu nhat: ");
            double w = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhap chieu dai hinh chu nhat: ");
            double h = Double.parseDouble(scanner.nextLine());
            double rectResult = calculator.rectangleArea(w, h);
            System.out.println("Dien tich hinh chu nhat la: " + rectResult);
            System.out.println();

            System.out.print("Nhap ban kinh hinh tron: ");
            double r = Double.parseDouble(scanner.nextLine());
            double circleResult = calculator.circleArea (r);
            System.out.println("Dien tich hinh tron la: " + circleResult);
            System.out.println();

            System.out.print("Nhap chieu dai canh day hinh tam giac: ");
            double base = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhap chieu dai 1 canh hinh tam giac: ");
            double height = Double.parseDouble(scanner.nextLine());
            double triResult = calculator.triangleArea(base, height);
            System.out.print("Dien tich hinh tam giac la: " + triResult);
            System.out.println();

            scanner.close();
        } catch (Exception e) {
        System.out.println("Loi client: " + e.getMessage());
        e.printStackTrace();
        }

    } 
}