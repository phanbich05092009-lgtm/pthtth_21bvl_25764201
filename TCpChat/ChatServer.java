
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {

    public static final int PORT = 12345;

    public static final String[] ROOMS = {
        "Study", "Game", "TraiNghiem"
    };

    // Danh sach client trong tung phong
    private static final Map<String, Set<ClientHandler>> rooms =
            new HashMap<>();

    static {
        for (String room : ROOMS) {
            rooms.put(room, new HashSet<>());
        }
    }

    public static synchronized boolean join(
            ClientHandler client, String room) {

        if (!rooms.containsKey(room)) {
            return false;
        }

        leave(client);

        rooms.get(room).add(client);
        client.setRoom(room);

        broadcast(room, client.getName()
                + " da vao phong.");

        client.sendMessage("Ban dang o phong " + room);
        return true;
    }

    public static synchronized void leave(
            ClientHandler client) {

        String room = client.getClientRoom();

        if (room != null) {
            rooms.get(room).remove(client);

            broadcast(room, client.getClientName()
                    + " da roi phong.");

            client.setRoom(null);
        }
    }

    public static synchronized void broadcast(
            String room, String message) {

        if (room == null || !rooms.containsKey(room)) {
            return;
        }

        for (ClientHandler client : rooms.get(room)) {
            client.sendMessage(message);
        }
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket =
                     new ServerSocket(PORT)) {

            System.out.println(
                    "Chat Server dang chay tai cong " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println(
                        "Client moi: " + socket.getInetAddress());

                ClientHandler client =
                        new ClientHandler(socket);

                client.start();
            }

        } catch (IOException e) {
            System.out.println("Loi Server: " + e.getMessage());
        }
    }
}
