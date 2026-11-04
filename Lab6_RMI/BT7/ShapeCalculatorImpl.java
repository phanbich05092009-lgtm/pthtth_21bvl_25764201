import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeCalculatorImpl extends UnicastRemoteObject implements ShapeCalculator {
    public ShapeCalculatorImpl() throws RemoteException {
        super();
    }
    @Override 
    public double rectangleArea(double width, double height) throws RemoteException {
        System.out.println("Server dang tinh dien tich hình chu nhat: rong = " + width + "dai = " + height);
        return width*height;
    }
    @Override 
    public double circleArea (double radius) throws RemoteException {
        System.out.println("Server dang tinh dien tich hinh tron: bán kinh = " + radius);
        return Math.PI * radius * radius;
    }
    @Override 
    public double triangleArea(double base, double height) throws RemoteException {
        System.out.println("Server dang tinh dien tich hinh tam giac: day = " + base + "chieu cao = " + height);
        return 0.5*base*height;
    }
    
}