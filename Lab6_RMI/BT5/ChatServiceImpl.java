import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private StringBuilder chatHistory;

    public ChatServiceImpl() throws RemoteException {
        super();
        chatHistory = new StringBuilder();
    }

    @Override
    public synchronized void sendMessage(String sender, String message) throws RemoteException {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String formattedMessage = "[" + time + "] " + sender + ": " + message + "\n";
        chatHistory.append(formattedMessage);
        System.out.print(formattedMessage); // In ra màn hình Server theo dõi
    }

    @Override
    public synchronized String getChatHistory() throws RemoteException {
        return chatHistory.toString();
    }
}