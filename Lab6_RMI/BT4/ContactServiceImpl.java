import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    // Lưu trữ danh bạ trên server
    private Map<String, String> contacts = new HashMap<>();

    public ContactServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
        System.out.println("Da them lien he: " + name + " - " + phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        return contacts.getOrDefault(name, "Khong tim thay lien he!");
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return true;
        }
        return false;
    }
}
