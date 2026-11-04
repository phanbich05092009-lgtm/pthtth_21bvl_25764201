import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void sendMessage(String sender, String message) throws RemoteException;
    String getChatHistory() throws RemoteException;
}