import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public   class BankAccountImpl extends UnicastRemoteObject implements BankAccount {
    private double balance;

    public BankAccountImpl (double initialBalance) throws RemoteException {
        super();
        this.balance = initialBalance;
    }
    @Override 
    public synchronized double getBalance() throws RemoteException {
        System.out.println("Bat dau kiem tra so du tai khoan!!! So du tai khoan cua ban:" + balance);
        return balance;
    }
    @Override 
    public synchronized void deposit (double amount) throws RemoteException {
        if (amount > 0){
            balance += amount;
            System.out.println("So tien ban gui la:" + amount + "So du la:" + balance);

        } else {
            System.out.println("So tien khong hop le!!!");
        }
    }
    @Override 
    public synchronized boolean withdraw (double amount) throws RemoteException {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Rut tien thanh cong!!!"+ balance);
            return true;
        } else {
            System.out.println("Rut tien khong thanh cong! So du cua ban khong du");
            return false;
        }
    }
}